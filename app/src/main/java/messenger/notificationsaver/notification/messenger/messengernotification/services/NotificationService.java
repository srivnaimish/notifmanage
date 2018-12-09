package messenger.notificationsaver.notification.messenger.messengernotification.services;

import android.app.Notification;
import android.os.AsyncTask;
import android.os.Looper;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

import org.json.JSONArray;

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

        JSONArray installedPackages = sharedPrefUtil.getInstalledApps();
        if (!Utilities.isEmpty(installedPackages) && !installedPackages.toString().contains(sbn.getPackageName())) {
            return;
        }

        if (sbn.getPackageName().equalsIgnoreCase(getPackageName())) {
            return;
        }

        Notification notification = sbn.getNotification();

        String title = notification.extras.getString("android.title");
        String text = notification.extras.getString("android.text");
        if (Utilities.isEmpty(title) || Utilities.isEmpty(text)) {
            return;
        }

        NotificationEntity notificationEntity = new NotificationEntity();
        notificationEntity.setId(sbn.getId());
        notificationEntity.setAppPackage(sbn.getPackageName());
        notificationEntity.setTitle(title);
        notificationEntity.setText(text);
        notificationEntity.setTime(sbn.getPostTime());

        /*Bitmap id = sbn.getNotification().largeIcon;
        if (id != null) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            id.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();

        }*/

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
}
