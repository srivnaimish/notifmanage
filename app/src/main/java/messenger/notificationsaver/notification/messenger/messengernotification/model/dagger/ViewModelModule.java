package messenger.notificationsaver.notification.messenger.messengernotification.model.dagger;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;


import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import messenger.notificationsaver.notification.messenger.messengernotification.model.ViewModelFactory;
import messenger.notificationsaver.notification.messenger.messengernotification.model.dagger.qualifiers.ViewModelKey;
import messenger.notificationsaver.notification.messenger.messengernotification.view.activity.titleWiseNotifications.TitleWiseViewModel;
import messenger.notificationsaver.notification.messenger.messengernotification.view.fragment.allNotifications.AllNotificationsViewModel;

/**
 * Created by naimish on 15/11/2018
 */
@Module
public class ViewModelModule {

    @Provides
    @IntoMap
    @ViewModelKey(AllNotificationsViewModel.class)
    ViewModel bindAllNotificationsViewModel(AllNotificationsViewModel viewModel) {
        return viewModel;
    }

    @Provides
    @IntoMap
    @ViewModelKey(TitleWiseViewModel.class)
    ViewModel bindTitleWiseViewModel(TitleWiseViewModel viewModel) {
        return viewModel;
    }

    @Provides
    ViewModelProvider.Factory provideViewModelFactory(ViewModelFactory factory) {
        return factory;
    }
}
