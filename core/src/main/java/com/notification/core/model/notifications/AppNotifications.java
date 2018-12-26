package com.notification.core.model.notifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;

import com.notification.core.R;
import com.notification.core.model.room.dao.NotificationDao;
import com.notification.core.utils.Constants;
import com.notification.core.utils.SharedPrefUtil;
import com.notification.core.utils.Utilities;
import com.notification.core.view.activity.landing.LandingActivity;

/**
 * Created by naimish on 09/12/2018
 */
public class AppNotifications {

    public static void publishNewNotification(Context context, NotificationDao notificationDao) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        if (!prefs.getBoolean(Constants.SHOW_NOTIFICATION, true)) {

            return;
        }

        Intent intent = new Intent(context, LandingActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, "New App Notifications")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Notifications")
                .setContentText(notificationDao.getUnreadNotificationsCount() + " unread Notifications")
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_notification)
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

    public static void removeNotification(Context context) {
        if (context == null) {
            return;
        }
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (notificationManager == null) {
            return;
        }
        notificationManager.cancel(Constants.MAIN_NOTIFICATION_ID);
    }
}
