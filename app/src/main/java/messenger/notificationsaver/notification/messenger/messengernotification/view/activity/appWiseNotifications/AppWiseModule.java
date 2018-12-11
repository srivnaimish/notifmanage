package messenger.notificationsaver.notification.messenger.messengernotification.view.activity.appWiseNotifications;

import android.arch.lifecycle.ViewModelProviders;

import dagger.Module;
import dagger.Provides;
import messenger.notificationsaver.notification.messenger.messengernotification.model.ViewModelFactory;
import messenger.notificationsaver.notification.messenger.messengernotification.model.dagger.inject.PerFragment;
import messenger.notificationsaver.notification.messenger.messengernotification.view.fragment.allNotifications.AllNotificationsFragment;

/**
 * Created by naimish on 10/12/2018
 */
@Module
public class AppWiseModule {
    @PerFragment
    @Provides
    AppWiseViewModel getModel(AllNotificationsFragment fragment, ViewModelFactory viewModelFactory) {
        return ViewModelProviders.of(fragment, viewModelFactory).get(AppWiseViewModel.class);
    }
}
