package messenger.notificationsaver.notification.messenger.messengernotification.application;

import android.content.Context;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * Created by naimish on 07/12/2018
 */
public class NotificationApplication extends DaggerApplication {

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //MultiDex.install(this);
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        NotificationAppComponent injector = DaggerNotificationAppComponent.builder()
                .application(this)
                .build();
        injector.inject(this);
        return injector;
    }
}
