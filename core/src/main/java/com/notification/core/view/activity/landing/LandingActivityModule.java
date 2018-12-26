package com.notification.core.view.activity.landing;

import dagger.Binds;
import dagger.Module;
import com.notification.core.model.dagger.inject.PerActivity;

/**
 * Created by naimish on 18/11/2018
 */
@Module
public abstract class LandingActivityModule {
    @Binds
    @PerActivity
    abstract LandingContract.Presenter provideLandingPresenter(LandingPresenter landingPresenter);

    @Binds
    @PerActivity
    abstract LandingContract.View provideLandingView(LandingActivity landingActivity);
}
