package messenger.notificationsaver.notification.messenger.messengernotification.utils;

/**
 * Created by naimish on 07/12/2018
 */
public class Constants {

    /*Shared Preferences*/
    public static final String SEEN_ONBOARDING = "seen_oboarding";
    public static final String AUTO_START = "auto_start";
    public static final String BATTERY_OPTIMIZATION = "battery_optimization";
    public static final String INSTALLED_APPS_PACKAGES = "installed_apps_packages";

    /*Notification ids*/
    public static final int MAIN_NOTIFICATION_ID = 9514;

    public static final int AD_REPEAT_POSITION = 3;
    public static final int AD_ROW = 1;

    public static final long ONE_SECOND_MILLIS = 1000;

    public static final long ONE_MINUTE_MILLIS = 60 * 1000;

    public static final long ONE_HOUR_MILLIS = 60 * 60 * 1000;

    public static final long ONE_DAY_MILLS = 24 * 60 * 60 * 1000L;

    public static final long HALF_DAY_MILLS = 12 * 60 * 60 * 1000L;

    public static final long ONE_YEAR_DAYS = 365L;

    public static final long ONE_WEEK_DAYS = 7L;

    public static final long ONE_WEEK_MILLS = ONE_WEEK_DAYS * ONE_DAY_MILLS;

    public static final long ONE_YEAR_MILLS = ONE_YEAR_DAYS * ONE_DAY_MILLS;

    public static final String blackList[] = {"com.android.vending", "com.android.providers.downloads"};
    public static final String PACKAGE_NAME = "package_name";
    public static final String NOTIFICATION_TITLE = "title";
}
