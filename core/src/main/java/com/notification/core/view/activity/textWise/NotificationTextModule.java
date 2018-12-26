package com.notification.core.view.activity.textWise;

import android.arch.lifecycle.ViewModelProviders;

import dagger.Module;
import dagger.Provides;
import com.notification.core.model.ViewModelFactory;
import com.notification.core.model.dagger.inject.PerActivity;

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
