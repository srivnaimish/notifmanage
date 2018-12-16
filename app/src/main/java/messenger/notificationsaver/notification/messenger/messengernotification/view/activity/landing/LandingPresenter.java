package messenger.notificationsaver.notification.messenger.messengernotification.view.activity.landing;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.NotificationManagerCompat;
import android.view.MenuItem;

import java.util.Set;

import javax.inject.Inject;

import messenger.notificationsaver.notification.messenger.messengernotification.R;
import messenger.notificationsaver.notification.messenger.messengernotification.model.dagger.qualifiers.ApplicationContext;
import messenger.notificationsaver.notification.messenger.messengernotification.model.notifications.AppNotifications;
import messenger.notificationsaver.notification.messenger.messengernotification.model.room.dao.NotificationDao;
import messenger.notificationsaver.notification.messenger.messengernotification.utils.SharedPrefUtil;
import messenger.notificationsaver.notification.messenger.messengernotification.utils.Utilities;
import messenger.notificationsaver.notification.messenger.messengernotification.view.activity.base.BaseActivityPresenter;

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
        switch (menuItem.getItemId()) {
            case R.id.search:
                view.openSearchActivity();
                break;
            case R.id.settings:
                break;
        }
        return false;
    }
}
