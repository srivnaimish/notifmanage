package messenger.notificationsaver.notification.messenger.messengernotification.services;

import android.app.Notification;
import android.os.AsyncTask;
import android.os.Looper;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.widget.Toast;

import java.util.regex.Pattern;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import messenger.notificationsaver.notification.messenger.messengernotification.model.notifications.AppNotifications;
import messenger.notificationsaver.notification.messenger.messengernotification.model.room.dao.NotificationDao;
import messenger.notificationsaver.notification.messenger.messengernotification.model.room.entity.NotificationEntity;
import messenger.notificationsaver.notification.messenger.messengernotification.utils.SharedPrefUtil;
import messenger.notificationsaver.notification.messenger.messengernotification.utils.Utilities;

/**
 * Created by naimish on 07/12/2018
 */
public class NotificationService extends NotificationListenerService {

    private static final String TAG = "NotificationService";

    @Inject
    NotificationDao notificationDao;

    @Inject
    SharedPrefUtil sharedPrefUtil;

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidInjection.inject(this);
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {

        Notification notification = sbn.getNotification();

        if (!shouldSaveNotification(sbn)) {
            return;
        }

        String category = notification.extras.getString(Notification.EXTRA_TEMPLATE);
        String notificationTitle = getNotificationTitle(sbn, notification);
        String notificationText = getNotificationText(category, notification);

        if (Utilities.isEmpty(notificationTitle) || Utilities.isEmpty(notificationText)) {
            return;
        }

        NotificationEntity notificationEntity = new NotificationEntity();
        notificationEntity.setNotificationId(sbn.getId());
        notificationEntity.setAppPackage(sbn.getPackageName());
        notificationEntity.setAppName(Utilities.getAppNameFromPackage(this, sbn.getPackageName()));
        notificationEntity.setTitle(notificationTitle.trim());
        notificationEntity.setText(notificationText.trim());
        notificationEntity.setTime(sbn.getPostTime());
        notificationEntity.setCategory(category);

        if (Looper.myLooper() == Looper.getMainLooper()) {
            AsyncTask.execute(() -> {
                notificationDao.insertNewNotification(notificationEntity);
                AppNotifications.publishNewNotification(this, notificationDao);
            });
            return;
        } else {
            notificationDao.insertNewNotification(notificationEntity);
            AppNotifications.publishNewNotification(this, notificationDao);
        }

        notificationDao.insertNewNotification(notificationEntity);
        AppNotifications.publishNewNotification(this, notificationDao);
    }

    private String getNotificationText(String category, Notification notification) {
        String text = notification.extras.getString(Notification.EXTRA_TEXT);

        if (category.equalsIgnoreCase("android.app.Notification$BigTextStyle")) {
            text = notification.extras.getString(Notification.EXTRA_BIG_TEXT);
        }

        if (text != null && text.matches("\\d+ new messages")) {
            return null;
        }

        return text;
    }

    private String getNotificationTitle(StatusBarNotification sbn, Notification notification) {
        String title = notification.extras.getString(Notification.EXTRA_TITLE);
        CharSequence convoTitle = notification.extras.getCharSequence(Notification.EXTRA_CONVERSATION_TITLE);
        if (!Utilities.isEmpty(convoTitle)) {
            title = convoTitle.toString();
        }

        String appName = Utilities.getAppNameFromPackage(this, sbn.getPackageName());

        if (title == null ||
                title.equalsIgnoreCase(appName) ||
                title.matches("\\d+ new messages")) return null;

        if (Pattern.matches("(.*?) \\(\\d+ messages\\)", title)) {
            title = title.substring(0, title.lastIndexOf(" ("));
        }

        return title;
    }

    private boolean shouldSaveNotification(StatusBarNotification sbn) {
        if (sbn.isOngoing()) return false;
        if (sbn.getPackageName().equalsIgnoreCase(getPackageName()))
            return false;    //Own app notification
        if (Utilities.isBlackListed(sbn.getPackageName())) return false;  //Blacklisted app
        if (!Utilities.isInstalledPackage(this, sbn.getPackageName())) return false;
        return true;
    }
}
