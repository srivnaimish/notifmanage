package messenger.notificationsaver.notification.messenger.messengernotification.utils;

import android.content.Context;
import android.content.Intent;

import messenger.notificationsaver.notification.messenger.messengernotification.view.activity.landing.LandingActivity;
import messenger.notificationsaver.notification.messenger.messengernotification.view.activity.onboarding.OnBoardingActivity;
import messenger.notificationsaver.notification.messenger.messengernotification.view.activity.splash.SplashActivity;

/**
 * Created by naimish on 07/12/2018
 */
public class IntentFactory {
    public static Intent getLandingActivity(Context context) {
        return new Intent(context, LandingActivity.class);
    }

    public static Intent getOnboardingActivity(Context context) {
        return new Intent(context, OnBoardingActivity.class);
    }

    public static Intent getNotificationAccessSetting() {
        return new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
    }
}
