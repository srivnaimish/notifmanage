package com.notification.core.model.room.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by naimish on 07/12/2018
 */
@Entity
public class NotificationEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    long id;

    @ColumnInfo(name = "recipient")
    @NonNull
    int recipient;

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

    @ColumnInfo(name = "app_name")
    @NonNull
    String appName;

    @ColumnInfo(name = "notification_read_status")
    boolean read;

    @ColumnInfo(name = "notification_tag")
    String tag;

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

    public int getRecipient() {
        return recipient;
    }

    public void setRecipient(int recipient) {
        this.recipient = recipient;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public String getAppName() {
        return appName;
    }

    public void setAppName(@NonNull String appName) {
        this.appName = appName;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}

