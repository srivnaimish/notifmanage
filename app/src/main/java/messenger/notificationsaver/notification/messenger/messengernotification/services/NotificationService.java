package messenger.notificationsaver.notification.messenger.messengernotification.services;

import android.app.Notification;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.widget.Toast;

import javax.inject.Inject;

import messenger.notificationsaver.notification.messenger.messengernotification.model.room.dao.NotificationDao;

/**
 * Created by naimish on 07/12/2018
 */
public class NotificationService extends NotificationListenerService {

    private static final String TAG = "NotificationService";

    @Inject
    NotificationDao notificationDao;

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        Toast.makeText(this, sbn.getPackageName(), Toast.LENGTH_SHORT).show();
        Notification notification = sbn.getNotification();
        String category = notification.category;
        Log.d(TAG, category + "\n" +
                notification.extras + "\n" +
                sbn.getPackageName() + "\n"
        );
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        super.onNotificationRemoved(sbn);
    }
}
