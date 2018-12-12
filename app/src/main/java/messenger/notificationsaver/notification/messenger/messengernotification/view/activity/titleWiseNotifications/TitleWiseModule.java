package messenger.notificationsaver.notification.messenger.messengernotification.view.activity.titleWiseNotifications;

import android.arch.lifecycle.ViewModelProviders;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import messenger.notificationsaver.notification.messenger.messengernotification.model.ViewModelFactory;
import messenger.notificationsaver.notification.messenger.messengernotification.model.dagger.inject.PerActivity;
import messenger.notificationsaver.notification.messenger.messengernotification.model.dagger.inject.PerFragment;
import messenger.notificationsaver.notification.messenger.messengernotification.view.fragment.allNotifications.AllNotificationsFragment;

/**
 * Created by naimish on 10/12/2018
 */
@Module
public class TitleWiseModule {
    @Provides
    @PerActivity
    TitleWiseViewModel getModel(TitleWiseActivity activity, ViewModelFactory viewModelFactory) {
        return ViewModelProviders.of(activity, viewModelFactory).get(TitleWiseViewModel.class);
    }
}
