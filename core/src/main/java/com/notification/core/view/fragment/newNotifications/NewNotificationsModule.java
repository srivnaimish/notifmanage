package com.notification.core.view.fragment.newNotifications;

import android.arch.lifecycle.ViewModelProviders;

import dagger.Module;
import dagger.Provides;
import com.notification.core.model.ViewModelFactory;
import com.notification.core.model.dagger.inject.PerFragment;
import com.notification.core.view.fragment.allNotifications.AllNotificationsFragment;
import com.notification.core.view.fragment.allNotifications.AllNotificationsViewModel;

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
