package messenger.notificationsaver.notification.messenger.messengernotification.model.room.dao;

import android.arch.lifecycle.LiveData;
import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import messenger.notificationsaver.notification.messenger.messengernotification.model.pojo.AllNotificationRow;
import messenger.notificationsaver.notification.messenger.messengernotification.model.room.entity.NotificationEntity;

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

    @Query("SELECT DISTINCT app_package,notification_time,notification_id,notification_title,notification_text,COUNT(CASE WHEN app_package = app_package AND notification_read_status = 0 THEN 1 END) as unread from NotificationEntity ORDER BY notification_time DESC")
    DataSource.Factory<Integer, AllNotificationRow> getAppsWithNotification();
}
