package messenger.notificationsaver.notification.messenger.messengernotification.model.room.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by naimish on 07/12/2018
 */
@Entity
public class NotificationEntity {

    @PrimaryKey
    @ColumnInfo(name = "id")
    int id;
}
