package messenger.notificationsaver.notification.messenger.messengernotification.model.dagger;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.google.firebase.analytics.FirebaseAnalytics;

import dagger.Module;
import dagger.Provides;
import messenger.notificationsaver.notification.messenger.messengernotification.application.NotificationApplication;
import messenger.notificationsaver.notification.messenger.messengernotification.model.dagger.inject.PerApplication;
import messenger.notificationsaver.notification.messenger.messengernotification.model.dagger.qualifiers.ApplicationContext;
import messenger.notificationsaver.notification.messenger.messengernotification.model.room.MessengerDB;
import messenger.notificationsaver.notification.messenger.messengernotification.model.room.dao.NotificationDao;

/**
 * Created by naimish on 06/12/2018.
 */
@Module
public class NotificationAppModule {

    @Provides
    @PerApplication
    @ApplicationContext
    Context provideContext(NotificationApplication application) {
        return application.getApplicationContext();
    }

    @Provides
    @PerApplication
    FirebaseAnalytics providesFirebaseAnalytics(@ApplicationContext Context context) {
        return FirebaseAnalytics.getInstance(context);
    }

    @Provides
    @PerApplication
    MessengerDB providesRoomDatabase(@ApplicationContext Context context) {
        return Room.databaseBuilder(context, MessengerDB.class, "MessengerDB")
                .build();
    }

    @Provides
    @PerApplication
    public NotificationDao provideNotificationDao(MessengerDB messengerDB) {
        return messengerDB.getNotificationDao();
    }
}
