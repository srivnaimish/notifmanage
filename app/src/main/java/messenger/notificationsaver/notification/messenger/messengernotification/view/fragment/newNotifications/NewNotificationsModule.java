package messenger.notificationsaver.notification.messenger.messengernotification.view.fragment.newNotifications;

import android.arch.lifecycle.ViewModelProviders;

import dagger.Module;
import dagger.Provides;
import messenger.notificationsaver.notification.messenger.messengernotification.model.ViewModelFactory;
import messenger.notificationsaver.notification.messenger.messengernotification.model.dagger.inject.PerFragment;
import messenger.notificationsaver.notification.messenger.messengernotification.view.fragment.allNotifications.AllNotificationsFragment;
import messenger.notificationsaver.notification.messenger.messengernotification.view.fragment.allNotifications.AllNotificationsViewModel;

/**
 * Created by naimish on 10/12/2018
 */
@Module
public class NewNotificationsModule {
    @PerFragment
    @Provides
    NewNotificationsViewModel getModel(NewNotificationsFragment fragment, ViewModelFactory viewModelFactory) {
        return ViewModelProviders.of(fragment, viewModelFactory).get(NewNotificationsViewModel.class);
    }
}
