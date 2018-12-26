package com.notification.core.view.activity.search;

import android.arch.lifecycle.ViewModelProviders;

import dagger.Module;
import dagger.Provides;
import com.notification.core.model.ViewModelFactory;
import com.notification.core.model.dagger.inject.PerActivity;
import com.notification.core.view.activity.textWise.NotificationTextActivity;
import com.notification.core.view.activity.textWise.NotificationTextViewModel;

/**
 * Created by naimish on 10/12/2018
 */
@Module
public class SearchModule {
    @Provides
    @PerActivity
    NotificationTextViewModel getModel(NotificationTextActivity activity, ViewModelFactory viewModelFactory) {
        return ViewModelProviders.of(activity, viewModelFactory).get(NotificationTextViewModel.class);
    }
}
