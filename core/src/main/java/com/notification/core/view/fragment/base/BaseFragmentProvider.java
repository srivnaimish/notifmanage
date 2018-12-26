package com.notification.core.view.fragment.base;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import com.notification.core.model.dagger.inject.PerFragment;
import com.notification.core.view.fragment.allNotifications.AllNotificationsFragment;
import com.notification.core.view.fragment.allNotifications.AllNotificationsModule;
import com.notification.core.view.fragment.newNotifications.NewNotificationsFragment;
import com.notification.core.view.fragment.newNotifications.NewNotificationsModule;

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
