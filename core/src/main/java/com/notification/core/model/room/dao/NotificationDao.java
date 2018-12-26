package com.notification.core.model.room.dao;

import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import com.notification.core.model.pojo.NotificationRow;
import com.notification.core.model.pojo.SearchRow;
import com.notification.core.model.room.entity.NotificationEntity;

/**
 * Created by naimish on 07/12/2018
 */
@Dao
public interface NotificationDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertNewNotification(NotificationEntity notificationEntity);

    @Query("SELECT * from NotificationEntity")
    List<NotificationEntity> getNotifications();

    @Query("SELECT COUNT(*) from NotificationEntity where notification_read_status = 0")
    int getUnreadNotificationsCount();

    @Query("Select app_package," +
            "notification_time," +
            "notification_category," +
            "notification_title," +
            "notification_text," +
            "notification_tag," +
            "recipient," +
            "COUNT(*) AS unread from NotificationEntity GROUP BY app_package ORDER BY notification_time DESC")
    DataSource.Factory<Integer, NotificationRow> getAppsWithNotification();

    @Query("Select app_package," +
            "notification_time," +
            "notification_category," +
            "notification_title," +
            "notification_text," +
            "notification_tag," +
            "recipient," +
            "COUNT(CASE WHEN notification_read_status = 0 THEN 1 END) AS unread from NotificationEntity WHERE app_package= :appPackage GROUP BY notification_title ORDER BY notification_time DESC")
    DataSource.Factory<Integer, NotificationRow> getTitleWiseNotifications(String appPackage);

    @Query("Select app_package," +
            "notification_time," +
            "notification_category," +
            "notification_title," +
            "notification_text," +
            "notification_tag," +
            "recipient," +
            "0 as unread from NotificationEntity WHERE app_package= :appPackage AND notification_title= :title ORDER BY notification_time DESC")
    DataSource.Factory<Integer, NotificationRow> getNotificationsTexts(String appPackage, String title);

    @Query("UPDATE NotificationEntity SET notification_read_status =1 where app_package = :packageName AND notification_title=:title")
    void readNotificationsOfPackage(String packageName, String title);

    @Query("Delete from NotificationEntity where notification_time-:currentTime>:deleteTimeSpan")
    void deleteNotifications(long deleteTimeSpan, long currentTime);

    @Query("Delete from NotificationEntity")
    void deleteNotifications();

    @Query("Select app_package," +
            "notification_time," +
            "notification_category," +
            "notification_title," +
            "notification_text," +
            "notification_tag," +
            "recipient," +
            "COUNT(*) AS unread from NotificationEntity WHERE notification_time>:time GROUP BY app_package ORDER BY notification_time DESC")
    DataSource.Factory<Integer, NotificationRow> getNotificationsSinceLastOpen(long time);

    @Query("Select app_package,notification_title,notification_text,notification_tag from NotificationEntity" +
            " WHERE app_name like :query" +
            " OR notification_title like :query" +
            " OR notification_text like :query ORDER BY notification_time DESC")
    DataSource.Factory<Integer, SearchRow> getSearchQuery(String query);
}
