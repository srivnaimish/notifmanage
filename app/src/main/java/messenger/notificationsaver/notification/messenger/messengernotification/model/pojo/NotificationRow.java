package messenger.notificationsaver.notification.messenger.messengernotification.model.pojo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Ignore;

/**
 * Created by naimish on 11/12/2018
 */
public class NotificationRow extends BaseRow {

    @ColumnInfo(name = "recipient")
    int recipient;

    @ColumnInfo(name = "notification_title")
    String title;

    @ColumnInfo(name = "notification_text")
    String text;

    @ColumnInfo(name = "notification_category")
    String category;

    @ColumnInfo(name = "app_package")
    String appPackage;

    @ColumnInfo(name = "notification_time")
    long time;

    @ColumnInfo(name = "notification_tag")
    String tag;

    @ColumnInfo(name = "unread")
    int unread;

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getRecipient() {
        return recipient;
    }

    public void setRecipient(int recipient) {
        this.recipient = recipient;
    }
}
