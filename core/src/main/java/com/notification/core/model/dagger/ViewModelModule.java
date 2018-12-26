package com.notification.core.model.dagger;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;


import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import com.notification.core.model.ViewModelFactory;
import com.notification.core.model.dagger.qualifiers.ViewModelKey;
import com.notification.core.view.activity.textWise.NotificationTextViewModel;
import com.notification.core.view.activity.search.SearchViewModel;
import com.notification.core.view.activity.titleWise.TitleWiseViewModel;
import com.notification.core.view.fragment.allNotifications.AllNotificationsViewModel;
import com.notification.core.view.fragment.newNotifications.NewNotificationsViewModel;

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
    @ViewModelKey(NewNotificationsViewModel.class)
    ViewModel bindNewNotificationsViewModel(NewNotificationsViewModel viewModel) {
        return viewModel;
    }

    @Provides
    @IntoMap
    @ViewModelKey(TitleWiseViewModel.class)
    ViewModel bindTitleWiseViewModel(TitleWiseViewModel viewModel) {
        return viewModel;
    }

    @Provides
    @IntoMap
    @ViewModelKey(NotificationTextViewModel.class)
    ViewModel bindNotificationTextViewModel(NotificationTextViewModel viewModel) {
        return viewModel;
    }

    @Provides
    @IntoMap
    @ViewModelKey(SearchViewModel.class)
    ViewModel bindSearchViewModel(SearchViewModel viewModel) {
        return viewModel;
    }

    @Provides
    ViewModelProvider.Factory provideViewModelFactory(ViewModelFactory factory) {
        return factory;
    }
}
