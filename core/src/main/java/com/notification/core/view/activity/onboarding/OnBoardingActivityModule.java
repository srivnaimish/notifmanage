package com.notification.core.view.activity.onboarding;

import dagger.Binds;
import dagger.Module;
import com.notification.core.model.dagger.inject.PerActivity;

/**
 * Created by naimish on 18/11/2018
 */
@Module
public abstract class OnBoardingActivityModule {
    @Binds
    @PerActivity
    abstract OnBoardingContract.Presenter provideOnboardingPresenter(OnBoardingPresenter mainActivity);

    @Binds
    @PerActivity
    abstract OnBoardingContract.View provideOnboardingView(OnBoardingActivity mainActivity);
}
