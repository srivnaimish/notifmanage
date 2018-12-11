package messenger.notificationsaver.notification.messenger.messengernotification.model.room.dao;

import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import messenger.notificationsaver.notification.messenger.messengernotification.model.pojo.NotificationRow;
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

    @Query("Select app_package,notification_time,notification_id,notification_title,notification_text,COUNT(*) AS unread from NotificationEntity GROUP BY app_package ORDER BY notification_time DESC")
    DataSource.Factory<Integer, NotificationRow> getAppsWithNotification();

    @Query("Select app_package,notification_time,notification_id,notification_title,notification_text,COUNT(*) AS unread from NotificationEntity GROUP BY app_package ORDER BY notification_time DESC")
    DataSource.Factory<Integer, NotificationRow> getAppWiseNotifications();
}
