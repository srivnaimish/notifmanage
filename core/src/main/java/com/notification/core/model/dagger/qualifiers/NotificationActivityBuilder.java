package com.notification.core.model.dagger.qualifiers;

import com.notification.core.model.dagger.inject.PerActivity;
import com.notification.core.services.NotificationService;
import com.notification.core.view.activity.landing.LandingActivity;
import com.notification.core.view.activity.landing.LandingActivityModule;
import com.notification.core.view.activity.onboarding.OnBoardingActivity;
import com.notification.core.view.activity.onboarding.OnBoardingActivityModule;
import com.notification.core.view.activity.search.SearchActivity;
import com.notification.core.view.activity.search.SearchModule;
import com.notification.core.view.activity.settings.SettingsActivity;
import com.notification.core.view.activity.settings.SettingsModule;
import com.notification.core.view.activity.splash.SplashActivity;
import com.notification.core.view.activity.splash.SplashActivityModule;
import com.notification.core.view.activity.textWise.NotificationTextActivity;
import com.notification.core.view.activity.textWise.NotificationTextModule;
import com.notification.core.view.activity.titleWise.TitleWiseActivity;
import com.notification.core.view.activity.titleWise.TitleWiseModule;
import com.notification.core.view.fragment.base.BaseFragmentProvider;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by anuragdalia on 01/08/18.
 */

@Module
public abstract class NotificationActivityBuilder {
    @PerActivity
    @ContributesAndroidInjector(modules = SplashActivityModule.class)
    abstract SplashActivity bindSplashActivity();

    @PerActivity
    @ContributesAndroidInjector(modules = OnBoardingActivityModule.class)
    abstract OnBoardingActivity bindOnBoardingActivity();

    @PerActivity
    @ContributesAndroidInjector(modules = {LandingActivityModule.class, BaseFragmentProvider.class})
    abstract LandingActivity bindLandingActivity();

    @PerActivity
    @ContributesAndroidInjector(modules = {TitleWiseModule.class})
    abstract TitleWiseActivity bindTitleWiseActivity();

    @PerActivity
    @ContributesAndroidInjector(modules = {NotificationTextModule.class})
    abstract NotificationTextActivity bindNotificationTextActivity();

    @PerActivity
    @ContributesAndroidInjector(modules = {SearchModule.class})
    abstract SearchActivity bindSearchActivity();

    @PerActivity
    @ContributesAndroidInjector(modules = {SettingsModule.class})
    abstract SettingsActivity bindSettingActivity();

    @PerActivity
    @ContributesAndroidInjector()
    abstract NotificationService providesNotificationService();
}
