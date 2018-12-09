package messenger.notificationsaver.notification.messenger.messengernotification.model.room;

/**
 * Created by naimish on 07/12/2018
 */
import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import messenger.notificationsaver.notification.messenger.messengernotification.model.room.dao.NotificationDao;
import messenger.notificationsaver.notification.messenger.messengernotification.model.room.entity.NotificationEntity;

@Database(entities = {NotificationEntity.class}, version = 1)
public abstract class MessengerDB extends RoomDatabase {
    private static final String TAG = "MessengerDB";

    public abstract NotificationDao getNotificationDao();

}
