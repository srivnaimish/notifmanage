package messenger.notificationsaver.notification.messenger.messengernotification.view.activity.landing;

import android.content.Context;

import messenger.notificationsaver.notification.messenger.messengernotification.view.activity.base.BaseActivityContract;

/**
 * Created by naimish on 07/12/2018
 */
public interface LandingContract {
    interface View extends BaseActivityContract.View {
        void requestNotificationAccess();

        void requestAutoStartPermission();

        void requestDisableBatteryOptimization();
    }

    interface Presenter extends BaseActivityContract.Presenter {
        boolean hasNotificationAccess(Context context);

        boolean isAutoStartEnabled();

        boolean isBatterySaverDisabled();

        void updateNotification();

        void saveLastSession();
    }
}
