package com.notification.core.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.notification.core.R;
import com.notification.core.view.activity.WebActivity;

/**
 * Created by naimish on 18/12/2018
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        String title = remoteMessage.getData().get("title");
        String body = remoteMessage.getData().get("body");
        String url = remoteMessage.getData().get("url");
        if (title == null || body == null) {
            return;
        }

        Intent intent = new Intent(this, WebActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("title", title);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, "Push Notifications")
                .setContentTitle(title)
                .setContentText(body)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setStyle(new NotificationCompat.BigTextStyle())
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_notification);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (notificationManager == null) {
            return;
        }

        if (Build.VERSION.SDK_INT >= 26) {
            notificationManager.createNotificationChannel(getChannel("Push Notifications"));
        }

        notificationManager.notify(0, notificationBuilder.build());
    }

    public static NotificationChannel getChannel(String channel_id) {

        NotificationChannel androidChannel = null;
        if (Build.VERSION.SDK_INT >= 26) {
            androidChannel = new NotificationChannel(channel_id,
                    channel_id, NotificationManager.IMPORTANCE_HIGH);
            androidChannel.enableLights(true);
            androidChannel.enableVibration(false);
            androidChannel.setLightColor(Color.RED);
            androidChannel.setShowBadge(false);
            androidChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
        }
        return androidChannel;
    }

    @Override
    public void onNewToken(String s) {
        FirebaseMessaging.getInstance().subscribeToTopic("push_notifications");
        super.onNewToken(s);
    }
}
