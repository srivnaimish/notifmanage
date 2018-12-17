package messenger.notificationsaver.notification.messenger.messengernotification.model.dagger;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import messenger.notificationsaver.notification.messenger.messengernotification.model.dagger.inject.PerActivity;
import messenger.notificationsaver.notification.messenger.messengernotification.services.NotificationService;
import messenger.notificationsaver.notification.messenger.messengernotification.view.activity.landing.LandingActivity;
import messenger.notificationsaver.notification.messenger.messengernotification.view.activity.landing.LandingActivityModule;
import messenger.notificationsaver.notification.messenger.messengernotification.view.activity.textWise.NotificationTextActivity;
import messenger.notificationsaver.notification.messenger.messengernotification.view.activity.textWise.NotificationTextModule;
import messenger.notificationsaver.notification.messenger.messengernotification.view.activity.onboarding.OnBoardingActivity;
import messenger.notificationsaver.notification.messenger.messengernotification.view.activity.onboarding.OnBoardingActivityModule;
import messenger.notificationsaver.notification.messenger.messengernotification.view.activity.search.SearchActivity;
import messenger.notificationsaver.notification.messenger.messengernotification.view.activity.search.SearchModule;
import messenger.notificationsaver.notification.messenger.messengernotification.view.activity.splash.SplashActivity;
import messenger.notificationsaver.notification.messenger.messengernotification.view.activity.splash.SplashActivityModule;
import messenger.notificationsaver.notification.messenger.messengernotification.view.activity.titleWise.TitleWiseActivity;
import messenger.notificationsaver.notification.messenger.messengernotification.view.activity.titleWise.TitleWiseModule;
import messenger.notificationsaver.notification.messenger.messengernotification.view.fragment.base.BaseFragmentProvider;

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
    @ContributesAndroidInjector()
    abstract NotificationService providesNotificationService();
}
