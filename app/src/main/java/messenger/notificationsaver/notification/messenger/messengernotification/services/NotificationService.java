package messenger.notificationsaver.notification.messenger.messengernotification.services;

import android.app.Notification;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Looper;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.RemoteInput;

import org.json.JSONArray;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import messenger.notificationsaver.notification.messenger.messengernotification.model.notifications.AppNotifications;
import messenger.notificationsaver.notification.messenger.messengernotification.model.room.dao.NotificationDao;
import messenger.notificationsaver.notification.messenger.messengernotification.model.room.entity.NotificationEntity;
import messenger.notificationsaver.notification.messenger.messengernotification.utils.Constants;
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

        if (sbn.getPackageName().equalsIgnoreCase(getPackageName())) {      //Own app notification
            return;
        }

        if (Constants.blackList.contains(sbn.getPackageName())) {   //Blacklisted app notification
            return;
        }

        if (!Utilities.isInstalledPackage(this, sbn.getPackageName())) {    //Not a drawer app
            return;
        }

        Notification notification = sbn.getNotification();

        String title = notification.extras.getString(Notification.EXTRA_TITLE);
        String text = notification.extras.getString(Notification.EXTRA_TEXT);
        int icon = notification.extras.getInt(Notification.EXTRA_LARGE_ICON, 0);

        if (Utilities.isEmpty(title) || Utilities.isEmpty(text)) {
            return;
        }

        title = title.trim();

        NotificationEntity notificationEntity = new NotificationEntity();
        notificationEntity.setNotificationId(sbn.getId());
        notificationEntity.setAppPackage(sbn.getPackageName());
        notificationEntity.setTitle(title);
        notificationEntity.setText(text);
        notificationEntity.setTime(sbn.getPostTime());
        notificationEntity.setIcon(icon);

        if (Looper.myLooper() == Looper.getMainLooper()) {
            AsyncTask.execute(() -> {
                notificationDao.insertNewNotification(notificationEntity);
                AppNotifications.publishNewNotification(this, notificationDao);
            });
            return;
        }

        notificationDao.insertNewNotification(notificationEntity);
        AppNotifications.publishNewNotification(this, notificationDao);
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        super.onNotificationRemoved(sbn);
    }

    public static NotificationCompat.Action getQuickReplyAction(Notification n, String packageName) {
        NotificationCompat.Action action = null;
        if (Build.VERSION.SDK_INT >= 24)
            return getQuickReplyAction(n);

        return getWearReplyAction(n);
    }

    private static NotificationCompat.Action getWearReplyAction(Notification n) {
        NotificationCompat.WearableExtender wearableExtender = new NotificationCompat.WearableExtender(n);
        for (NotificationCompat.Action action : wearableExtender.getActions()) {
            for (int x = 0; x < action.getRemoteInputs().length; x++) {
                RemoteInput remoteInput = action.getRemoteInputs()[x];
                if (remoteInput.getResultKey().toLowerCase().contains("reply"))
                    return action;
            }
        }
        return null;
    }

    private static NotificationCompat.Action getQuickReplyAction(Notification n) {
        for (int i = 0; i < NotificationCompat.getActionCount(n); i++) {
            NotificationCompat.Action action = NotificationCompat.getAction(n, i);
            for (int x = 0; x < action.getRemoteInputs().length; x++) {
                RemoteInput remoteInput = action.getRemoteInputs()[x];
                if (remoteInput.getResultKey().toLowerCase().contains("reply"))
                    return action;
            }
        }
        return null;
    }
}
