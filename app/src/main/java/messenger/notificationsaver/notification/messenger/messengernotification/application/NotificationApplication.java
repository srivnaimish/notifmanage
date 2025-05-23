package messenger.notificationsaver.notification.messenger.messengernotification.application;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.crashlytics.android.Crashlytics;
import com.facebook.stetho.Stetho;
import com.google.firebase.FirebaseApp;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import io.fabric.sdk.android.Fabric;

/**
 * Created by naimish on 07/12/2018
 */
public class NotificationApplication extends DaggerApplication {

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        FirebaseApp.initializeApp(this);
        Fabric.with(this, new Crashlytics());
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        NotificationAppComponent injector = DaggerNotificationAppComponent.builder()
                .application(this)
                .build();
        injector.inject(this);
        return injector;
    }

    public AndroidInjector<? extends DaggerApplication> getInjector(){
        return applicationInjector();
    }
}
