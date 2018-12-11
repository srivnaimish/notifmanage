package messenger.notificationsaver.notification.messenger.messengernotification.model.pojo;

import android.arch.persistence.room.ColumnInfo;

/**
 * Created by naimish on 11/12/2018
 */
public class AllNotificationRow extends BaseRow {

    @ColumnInfo(name = "notification_id")
    int id;

    @ColumnInfo(name = "notification_title")
    String title;

    @ColumnInfo(name = "notification_text")
    String text;

    @ColumnInfo(name = "app_package")
    String appPackage;

    @ColumnInfo(name = "notification_time")
    long time;

    @ColumnInfo(name = "unread")
    int unread;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setAppPackage(String appPackage) {
        this.appPackage = appPackage;
    }

    public String getAppPackage() {
        return appPackage;
    }

    public void setUnread(int unread) {
        this.unread = unread;
    }

    public int getUnread() {
        return unread;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getTime() {
        return time;
    }

}
