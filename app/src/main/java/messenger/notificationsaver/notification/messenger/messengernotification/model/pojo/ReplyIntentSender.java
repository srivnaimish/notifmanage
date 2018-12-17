package messenger.notificationsaver.notification.messenger.messengernotification.model.pojo;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class ReplyIntentSender {

    public Notification.Action action;

    public ReplyIntentSender(Notification.Action action) {
        this.action = action;
    }

    public boolean sendNativeIntent(Context context, String message) {
        for (android.app.RemoteInput rem : action.getRemoteInputs()) {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putCharSequence(rem.getResultKey(), message);
            android.app.RemoteInput.addResultsToIntent(action.getRemoteInputs(), intent, bundle);
            try {
                action.actionIntent.send(context, 0, intent);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
        return false;
    }
}