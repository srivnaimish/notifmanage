package messenger.notificationsaver.notification.messenger.messengernotification.model.notifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;

import messenger.notificationsaver.notification.messenger.messengernotification.R;
import messenger.notificationsaver.notification.messenger.messengernotification.model.room.dao.NotificationDao;
import messenger.notificationsaver.notification.messenger.messengernotification.utils.Constants;
import messenger.notificationsaver.notification.messenger.messengernotification.utils.Utilities;
import messenger.notificationsaver.notification.messenger.messengernotification.view.activity.landing.LandingActivity;

/**
 * Created by naimish on 09/12/2018
 */
public class AppNotifications {

    public static void publishNewNotification(Context context, NotificationDao notificationDao) {
        Intent intent = new Intent(context, LandingActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, "New App Notifications")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Notifications")
                .setContentText(notificationDao.getUnreadNotificationsCount() + " unread Notifications")
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setContentIntent(pendingIntent)
                .setAutoCancel(false)
                .setVibrate(new long[]{0})
                .setOngoing(true);

        Notification notification = mBuilder.build();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (notificationManager == null) {
            return;
        }

        if (Utilities.isOreoOrHigher()) {
            notificationManager.createNotificationChannel(getChannel("New App Notifications"));
        }

        notificationManager.notify(Constants.MAIN_NOTIFICATION_ID, notification);
    }

    public static NotificationChannel getChannel(String channel_id) {

        NotificationChannel androidChannel = null;
        if (Utilities.isOreoOrHigher()) {
            androidChannel = new NotificationChannel(channel_id,
                    channel_id, NotificationManager.IMPORTANCE_LOW);
            androidChannel.enableLights(true);
            androidChannel.enableVibration(false);
            androidChannel.setLightColor(Color.RED);
            androidChannel.setShowBadge(false);
            androidChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
        }
        return androidChannel;
    }

}
