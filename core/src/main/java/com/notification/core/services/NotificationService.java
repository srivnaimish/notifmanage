package com.notification.core.services;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

import org.greenrobot.eventbus.EventBus;

import java.util.regex.Pattern;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import com.notification.core.model.notifications.AppNotifications;
import com.notification.core.model.room.dao.NotificationDao;
import com.notification.core.model.room.entity.NotificationEntity;
import com.notification.core.utils.Constants;
import com.notification.core.utils.ReplyMap;
import com.notification.core.utils.SharedPrefUtil;
import com.notification.core.utils.Utilities;

/**
 * Created by naimish on 07/12/2018
 */
public class NotificationService extends NotificationListenerService {

    private static final String TAG = "NotificationService";

    @Inject
    NotificationDao notificationDao;


    private ReplyMap replyMap;

    @Inject
    SharedPrefUtil sharedPrefUtil;
    private String mPreviousTitle, mPreviousText;

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
        String notificationTitle = getNotificationTitle(notification);
        String notificationText = getNotificationText(category, notification);

        if (Utilities.isEmpty(notificationTitle) || Utilities.isEmpty(notificationText)) {
            return;
        }

        if (notificationTitle.equals(mPreviousTitle) && notificationText.equals(mPreviousText)) {
            return;
        }

        NotificationEntity notificationEntity = new NotificationEntity();
        notificationEntity.setRecipient(0);    //indicates 0 ->Received,1 ->Sent
        notificationEntity.setAppPackage(sbn.getPackageName());
        notificationEntity.setAppName(Utilities.getAppNameFromPackage(this, sbn.getPackageName()));
        notificationEntity.setTitle(notificationTitle.trim());
        notificationEntity.setText(notificationText.trim());
        notificationEntity.setTime(sbn.getPostTime());
        notificationEntity.setCategory(category);
        notificationEntity.setTag(sbn.getTag());

        AsyncTask.execute(() -> {
            notificationDao.insertNewNotification(notificationEntity);
            AppNotifications.publishNewNotification(this, notificationDao);
        });

        mPreviousTitle = notificationTitle;
        mPreviousText = notificationText;

        updateReplyMap(sbn);

    }

    private String getNotificationText(String category, Notification notification) {
        CharSequence contentText = notification.extras.getCharSequence(Notification.EXTRA_TEXT);
        if (!Utilities.isEmpty(category) && category.equalsIgnoreCase("android.app.Notification$BigTextStyle")) {
            contentText = notification.extras.getCharSequence(Notification.EXTRA_BIG_TEXT);
        }

        if (Utilities.isEmpty(contentText)) {
            return null;
        }

        String text = contentText.toString();

        if (text.matches("\\d+ new messages") || text.matches("\\d+ messages from +\\d+ chats")) {
            return null;
        }

        return text;
    }

    private String getNotificationTitle(Notification notification) {
        String title = notification.extras.getString(Notification.EXTRA_TITLE);
        CharSequence convoTitle = notification.extras.getCharSequence(Notification.EXTRA_CONVERSATION_TITLE);
        if (!Utilities.isEmpty(convoTitle)) {
            title = convoTitle.toString();
        }

        if (title == null ||
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

    private void updateReplyMap(StatusBarNotification sbn) {
        Notification.Action actions[] = sbn.getNotification().actions;

        if (Utilities.isEmpty(actions)) {
            return;
        }

        for (Notification.Action act : actions) {
            if (act != null && act.getRemoteInputs() != null) {
                if (act.title.toString().contains("Reply")) {
                    if (act.getRemoteInputs() != null) {
                        replyMap.put(sbn.getTag(), act);
                    }
                }
            }
        }
    }

    @Override
    public void onListenerConnected() {
        super.onListenerConnected();
        replyMap = new ReplyMap(50);

        IntentFilter filter = new IntentFilter();
        filter.addAction(Constants.SERVICE_REQUEST_ACTION);
        registerReceiver(receiver, filter);
    }

    @Override
    public void onListenerDisconnected() {
        super.onListenerDisconnected();
        unregisterReceiver(receiver);
        replyMap.clear();
    }

    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent == null || intent.getAction() == null) {
                return;
            }

            if (!intent.getAction().equals(Constants.SERVICE_REQUEST_ACTION)) {
                return;
            }

            if (Utilities.isEmpty(replyMap)) {
                return;
            }

            String tag = intent.getStringExtra(Constants.NOTIFICATION_TAG);

            if (Utilities.isEmpty(tag) || replyMap.get(tag) == null) {
                return;
            }

            EventBus.getDefault().post(replyMap.get(tag));
        }
    };

}
