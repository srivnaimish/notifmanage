package com.notification.core.view.activity.titleWise;

import android.arch.lifecycle.ViewModelProviders;

import dagger.Module;
import dagger.Provides;
import com.notification.core.model.ViewModelFactory;
import com.notification.core.model.dagger.inject.PerActivity;

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
