package messenger.notificationsaver.notification.messenger.messengernotification.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import messenger.notificationsaver.notification.messenger.messengernotification.view.activity.landing.LandingActivity;
import messenger.notificationsaver.notification.messenger.messengernotification.view.activity.settings.SettingsActivity;
import messenger.notificationsaver.notification.messenger.messengernotification.view.activity.textWise.NotificationTextActivity;
import messenger.notificationsaver.notification.messenger.messengernotification.view.activity.onboarding.OnBoardingActivity;
import messenger.notificationsaver.notification.messenger.messengernotification.view.activity.search.SearchActivity;
import messenger.notificationsaver.notification.messenger.messengernotification.view.activity.titleWise.TitleWiseActivity;

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

    public static final Intent[] AUTO_START_INTENTS = {
            new Intent().setComponent(new ComponentName("com.samsung.android.lool",
                    "com.samsung.android.sm.ui.battery.BatteryActivity")),
            new Intent("miui.intent.action.OP_AUTO_START").addCategory(Intent.CATEGORY_DEFAULT),
            new Intent().setComponent(new ComponentName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity")),
            new Intent().setComponent(new ComponentName("com.letv.android.letvsafe", "com.letv.android.letvsafe.AutobootManageActivity")),
            new Intent().setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.optimize.process.ProtectActivity")),
            new Intent().setComponent(new ComponentName("com.coloros.safecenter", "com.coloros.safecenter.permission.startup.StartupAppListActivity")),
            new Intent().setComponent(new ComponentName("com.coloros.safecenter", "com.coloros.safecenter.startupapp.StartupAppListActivity")),
            new Intent().setComponent(new ComponentName("com.oppo.safe", "com.oppo.safe.permission.startup.StartupAppListActivity")),
            new Intent().setComponent(new ComponentName("com.iqoo.secure", "com.iqoo.secure.ui.phoneoptimize.AddWhiteListActivity")),
            new Intent().setComponent(new ComponentName("com.iqoo.secure", "com.iqoo.secure.ui.phoneoptimize.BgStartUpManager")),
            new Intent().setComponent(new ComponentName("com.vivo.permissionmanager", "com.vivo.permissionmanager.activity.BgStartUpManagerActivity")),
            new Intent().setComponent(new ComponentName("com.asus.mobilemanager", "com.asus.mobilemanager.entry.FunctionActivity")).setData(
                    Uri.parse("mobilemanager://function/entry/AutoStart"))
    };

    public static Intent getBatteryOptimizationIntent(Context context) {
        String packageName = context.getPackageName();
        Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + packageName));
        return intent;
    }

    public static Intent getTitleWiseActivity(Context context, String appPackage) {
        Intent intent = new Intent(context, TitleWiseActivity.class);
        intent.putExtra(Constants.PACKAGE_NAME, appPackage);
        return intent;
    }

    public static Intent getNotificationTextActivity(Context context, String appPackage, String title) {
        Intent intent = new Intent(context, NotificationTextActivity.class);
        intent.putExtra(Constants.PACKAGE_NAME, appPackage);
        intent.putExtra(Constants.NOTIFICATION_TITLE, title);
        return intent;
    }

    public static Intent getSearchActivity(Context context) {
        return new Intent(context, SearchActivity.class);
    }

    public static Intent getSettingsActivity(Context context) {
        return new Intent(context, SettingsActivity.class);
    }
}
