package com.notification.core.view.activity.splash;

import dagger.Binds;
import dagger.Module;
import com.notification.core.model.dagger.inject.PerActivity;

/**
 * Created by anuragdalia on 19/07/18.
 */

@Module
public abstract class SplashActivityModule {
    @Binds
    @PerActivity
    abstract SplashContract.Presenter provideSplashPresenter(SplashPresenter mainActivity);

    @Binds
    @PerActivity
    abstract SplashContract.View provideSplashView(SplashActivity mainActivity);
}