package messenger.notificationsaver.notification.messenger.messengernotification.application;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import messenger.notificationsaver.notification.messenger.messengernotification.model.dagger.NotificationActivityBuilder;
import messenger.notificationsaver.notification.messenger.messengernotification.model.dagger.NotificationAppModule;
import messenger.notificationsaver.notification.messenger.messengernotification.model.dagger.ViewModelModule;
import messenger.notificationsaver.notification.messenger.messengernotification.model.dagger.inject.PerApplication;

/**
 * Created by naimish on 07/12/2018
 */
@Component(modules = {AndroidSupportInjectionModule.class, NotificationAppModule.class, NotificationActivityBuilder.class, ViewModelModule.class})
@PerApplication
public interface NotificationAppComponent extends AndroidInjector<NotificationApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(NotificationApplication application);

        NotificationAppComponent build();
    }
}

