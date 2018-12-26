package com.notification.core.view.fragment.allNotifications;

import android.arch.lifecycle.ViewModelProviders;

import dagger.Module;
import dagger.Provides;
import com.notification.core.model.ViewModelFactory;
import com.notification.core.model.dagger.inject.PerFragment;

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
