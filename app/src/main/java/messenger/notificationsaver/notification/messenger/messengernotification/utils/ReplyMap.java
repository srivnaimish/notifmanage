package messenger.notificationsaver.notification.messenger.messengernotification.utils;

import android.app.Notification;

import java.util.LinkedHashMap;

/**
 * Created by naimish on 25/12/2018
 */
public class ReplyMap extends LinkedHashMap<String, Notification.Action> {

    int maxSize;

    public ReplyMap(int maxSize) {
        super(maxSize);
        this.maxSize = maxSize;
    }

    @Override
    protected boolean removeEldestEntry(Entry<String, Notification.Action> eldest) {
        return size() > maxSize;
    }
}
