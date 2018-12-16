package messenger.notificationsaver.notification.messenger.messengernotification.view.fragment.base;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import messenger.notificationsaver.notification.messenger.messengernotification.model.dagger.inject.PerFragment;
import messenger.notificationsaver.notification.messenger.messengernotification.view.fragment.allNotifications.AllNotificationsFragment;
import messenger.notificationsaver.notification.messenger.messengernotification.view.fragment.allNotifications.AllNotificationsModule;
import messenger.notificationsaver.notification.messenger.messengernotification.view.fragment.newNotifications.NewNotificationsFragment;
import messenger.notificationsaver.notification.messenger.messengernotification.view.fragment.newNotifications.NewNotificationsModule;

/**
 * Created by naimish on 10/12/2018
 */
@Module
public abstract class BaseFragmentProvider {

    @PerFragment
    @ContributesAndroidInjector(modules = {AllNotificationsModule.class})
    abstract AllNotificationsFragment providesAllNotificationsFragment();

    @PerFragment
    @ContributesAndroidInjector(modules = {NewNotificationsModule.class})
    abstract NewNotificationsFragment providesNewNotificationsFragment();
}
