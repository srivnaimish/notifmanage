package messenger.notificationsaver.notification.messenger.messengernotification.view.activity.textWise;

import android.arch.lifecycle.ViewModelProviders;

import dagger.Module;
import dagger.Provides;
import messenger.notificationsaver.notification.messenger.messengernotification.model.ViewModelFactory;
import messenger.notificationsaver.notification.messenger.messengernotification.model.dagger.inject.PerActivity;

/**
 * Created by naimish on 10/12/2018
 */
@Module
public class NotificationTextModule {
    @Provides
    @PerActivity
    NotificationTextViewModel getModel(NotificationTextActivity activity, ViewModelFactory viewModelFactory) {
        return ViewModelProviders.of(activity, viewModelFactory).get(NotificationTextViewModel.class);
    }
}
