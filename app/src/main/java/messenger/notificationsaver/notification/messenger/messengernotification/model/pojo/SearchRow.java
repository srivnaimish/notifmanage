package messenger.notificationsaver.notification.messenger.messengernotification.model.pojo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Ignore;

/**
 * Created by naimish on 11/12/2018
 */
public class SearchRow extends BaseRow {

    @ColumnInfo(name = "notification_title")
    String title;

    @ColumnInfo(name = "notification_text")
    String text;

    @ColumnInfo(name = "app_package")
    String appPackage;

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


}