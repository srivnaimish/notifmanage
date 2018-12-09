package messenger.notificationsaver.notification.messenger.messengernotification.view.fragment.allNotifications;

import android.arch.lifecycle.ViewModelProviders;

import dagger.Module;
import dagger.Provides;
import messenger.notificationsaver.notification.messenger.messengernotification.model.ViewModelFactory;
import messenger.notificationsaver.notification.messenger.messengernotification.model.dagger.inject.PerFragment;

/**
 * Created by naimish on 10/12/2018
 */
@Module
public class AllNotificationsModule {
    @PerFragment
    @Provides
    AllNotificationsViewModel getModel(AllNotificationsFragment fragment, ViewModelFactory viewModelFactory) {
        return ViewModelProviders.of(fragment, viewModelFactory).get(AllNotificationsViewModel.class);
    }
}
