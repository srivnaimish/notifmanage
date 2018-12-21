package messenger.notificationsaver.notification.messenger.messengernotification.services;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcel;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Method;
import java.util.List;
import java.util.regex.Pattern;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import messenger.notificationsaver.notification.messenger.messengernotification.model.notifications.AppNotifications;
import messenger.notificationsaver.notification.messenger.messengernotification.model.pojo.ReplyIntentSender;
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

    private String mPreviousTag;

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
        String notificationTitle = getNotificationTitle(sbn, notification);
        String notificationText = getNotificationText(category, notification);

        if (Utilities.isEmpty(notificationTitle) || Utilities.isEmpty(notificationText)) {
            return;
        }

        if (notificationTitle.equalsIgnoreCase(mPreviousTitle) && notificationTitle.equalsIgnoreCase(mPreviousText)) {
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


        AsyncTask.execute(() -> {
            notificationDao.insertNewNotification(notificationEntity);
            AppNotifications.publishNewNotification(this, notificationDao);
        });

        mPreviousTitle = notificationTitle;
        mPreviousText = notificationText;

        /*if (sendReply(sbn) != null) {
            //sendReply(sbn).sendNativeIntent(this, "HEY");
        }*/

        /*try {
            Bundle bundle = new Bundle();
            PendingIntent pendingIntent = notification.contentIntent;
            bundle.putParcelable("pi", pendingIntent);
            PendingIntent pi = bundle.getParcelable("pi");
            pi.send();
        } catch (Exception e) {
            Log.e(getClass().getSimpleName(), "GENERATED ERROR", e);
        }
*/

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

    private String getNotificationTitle(StatusBarNotification sbn, Notification notification) {
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

    public ReplyIntentSender sendReply(StatusBarNotification statusBarNotification) {

        Notification.Action actions[] = statusBarNotification.getNotification().actions;

        for (Notification.Action act : actions) {
            if (act != null && act.getRemoteInputs() != null) {
                if (act.title.toString().contains("Reply")) {
                    if (act.getRemoteInputs() != null) {
                        return new ReplyIntentSender(act);
                    }
                }
            }
        }
        return null;
    }

}
