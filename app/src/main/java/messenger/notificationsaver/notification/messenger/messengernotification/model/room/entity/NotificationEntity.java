package messenger.notificationsaver.notification.messenger.messengernotification.model.room.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

/**
 * Created by naimish on 07/12/2018
 */
@Entity(primaryKeys = {"notification_id", "notification_title", "notification_text", "app_package"})
public class NotificationEntity {

    @ColumnInfo(name = "notification_id")
    @NonNull
    int notificationId;

    @ColumnInfo(name = "notification_title")
    @NonNull
    String title;

    @ColumnInfo(name = "notification_text")
    @NonNull
    String text;

    @ColumnInfo(name = "notification_category")
    String category;

    @ColumnInfo(name = "app_package")
    @NonNull
    String appPackage;

    @ColumnInfo(name = "notification_read_status")
    boolean read;

    @ColumnInfo(name = "notification_time")
    long time;

    public void setTitle(String title) {
        this.title = title;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setText(String text) {
        this.text = text;
    }

    @NonNull
    public String getText() {
        return text;
    }

    public void setAppPackage(String appPackage) {
        this.appPackage = appPackage;
    }

    @NonNull
    public String getAppPackage() {
        return appPackage;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public boolean isRead() {
        return read;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getTime() {
        return time;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}
