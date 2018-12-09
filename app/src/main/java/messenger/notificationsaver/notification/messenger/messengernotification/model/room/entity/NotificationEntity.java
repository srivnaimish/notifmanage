package messenger.notificationsaver.notification.messenger.messengernotification.model.room.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by naimish on 07/12/2018
 */
@Entity(primaryKeys = {"id", "app_package"})
public class NotificationEntity {

    @NonNull
    @ColumnInfo(name = "id")
    int id;

    @ColumnInfo(name = "title")
    String title;

    @ColumnInfo(name = "text")
    String text;

    @ColumnInfo(name = "app_package")
    @NonNull
    String appPackage;

    @ColumnInfo(name = "read_status")
    boolean read;

    @ColumnInfo(name = "time")
    long time;

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
}
