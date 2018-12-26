package com.notification.core.view.activity.landing;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.NotificationManagerCompat;
import android.view.MenuItem;

import java.util.Set;

import javax.inject.Inject;

import com.notification.core.R;
import com.notification.core.model.dagger.qualifiers.ApplicationContext;
import com.notification.core.model.notifications.AppNotifications;
import com.notification.core.model.room.dao.NotificationDao;
import com.notification.core.utils.Constants;
import com.notification.core.utils.SharedPrefUtil;
import com.notification.core.utils.Utilities;
import com.notification.core.view.activity.base.BaseActivityPresenter;

/**
 * Created by naimish on 07/12/2018
 */
public class LandingPresenter extends BaseActivityPresenter<LandingContract.View> implements LandingContract.Presenter {

    @Inject
    SharedPrefUtil sharedPrefUtil;

    @Inject
    NotificationDao notificationDao;

    @Inject
    @ApplicationContext
    protected Context context;

    @Inject
    public LandingPresenter(LandingContract.View view) {
        super(view);
    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public void subscribe() {

    }

    @Override
    public boolean hasNotificationAccess(Context context) {
        Set<String> enabledPackages = NotificationManagerCompat.getEnabledListenerPackages(context);
        if (enabledPackages.contains(context.getPackageName())) {
            return true;
        }

        view.requestNotificationAccess();
        return false;
    }

    @Override
    public boolean isAutoStartEnabled() {
        if (sharedPrefUtil.isAutoStartEnabled()) {
            return true;
        }

        view.requestAutoStartPermission();
        return false;
    }

    @Override
    public boolean isBatterySaverDisabled() {
        if (!Utilities.isMarshmallowOrHigher()) {
            return true;
        }

        if (sharedPrefUtil.isBatteryOptimizationDisabled()) {
            return true;
        }

        view.requestDisableBatteryOptimization();
        return false;
    }

    @Override
    public void updateNotification() {
        AsyncTask.execute(() -> AppNotifications.publishNewNotification(context, notificationDao));
    }

    @Override
    public void saveLastSession() {

        sharedPrefUtil.setLastSessionTime(System.currentTimeMillis());
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        int i = menuItem.getItemId();
        if (i == R.id.search) {
            view.openSearchActivity();

        } else if (i == R.id.settings) {
            view.openSettingsActivity();

        }
        return false;
    }
}
