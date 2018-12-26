package com.notification.core.model.dagger;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.notification.core.model.dagger.inject.PerApplication;
import com.notification.core.model.dagger.qualifiers.ApplicationContext;
import com.notification.core.model.room.MessengerDB;
import com.notification.core.model.room.dao.NotificationDao;

import dagger.Module;
import dagger.Provides;

/**
 * Created by naimish on 26/12/2018
 */
@Module
public class CoreAppModule {

    @Provides
    @PerApplication
    MessengerDB providesRoomDatabase(@ApplicationContext Context context) {
        return Room.databaseBuilder(context, MessengerDB.class, "NotisaveDB")
                .build();
    }

    @Provides
    @PerApplication
    public NotificationDao provideNotificationDao(MessengerDB messengerDB) {
        return messengerDB.getNotificationDao();
    }
}
