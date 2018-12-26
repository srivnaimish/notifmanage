package messenger.notificationsaver.notification.messenger.messengernotification.dagger;

import android.content.Context;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.notification.core.model.dagger.CoreAppModule;
import com.notification.core.model.dagger.inject.PerApplication;
import com.notification.core.model.dagger.qualifiers.ApplicationContext;

import dagger.Module;
import dagger.Provides;
import messenger.notificationsaver.notification.messenger.messengernotification.application.NotificationApplication;

/**
 * Created by naimish on 06/12/2018.
 */
@Module(includes = CoreAppModule.class)
public class AppModule {

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
}
