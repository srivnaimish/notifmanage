package messenger.notificationsaver.notification.messenger.messengernotification.view.activity.splash;

import dagger.Binds;
import dagger.Module;
import messenger.notificationsaver.notification.messenger.messengernotification.model.dagger.inject.PerActivity;

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