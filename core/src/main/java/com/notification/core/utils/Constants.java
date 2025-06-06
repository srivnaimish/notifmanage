package com.notification.core.utils;

import android.os.Build;

import com.notification.core.BuildConfig;

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

    public static final int AD_REPEAT_POSITION = 4;
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

    public static final String blackList[] = {"com.android.vending"};
    public static final String PACKAGE_NAME = "package_name";
    public static final String NOTIFICATION_TITLE = "title";
    public static final String NOTIFICATION_TAG = "tag";

    public static final String DEFAULT_CHAT_VALUE = "com.snapchat.android;com.google.android.talk;com.google.android.apps.fireball;com.Slack;com.yahoo.mobile.client.android.im;com.viber.voip;com.whatsapp;com.facebook.mlite;com.facebook.orca;org.telegram.messenger;jp.naver.line.android;com.linecorp.linelite;com.kakao.talk;com.tencent.mm,com.instagram.android";

    public static final String LAST_SESSION = "last_session";

    public static final String SHOW_NOTIFICATION = "show_notification";
    public static final String SERVICE_REQUEST_ACTION = "service_request";

    public static final int RECEIVED_MESSAGE_TYPE = 0;
    public static final int SENT_MESSAGE_TYPE = 1;

    public static final String APP_URL = "https://play.google.com/store/apps/details?id=messenger.notificationsaver.notification.messenger.messengernotification";

    public static String INTERSTITIAL_HOME = BuildConfig.DEBUG ? "YOUR_PLACEMENT_ID" : "1060491034146212_1060501154145200";
    public static String BANNER_TITLE = BuildConfig.DEBUG ? "YOUR_PLACEMENT_ID" : "1060491034146212_1060492830812699";
    public static String BANNER_TEXT = BuildConfig.DEBUG ? "YOUR_PLACEMENT_ID" : "1060491034146212_1060503484144967";
    public static String BANNER_SETTINGS = BuildConfig.DEBUG ? "YOUR_PLACEMENT_ID" : "1060491034146212_1060503860811596";
    public static String NATIVE_APPS = BuildConfig.DEBUG ? "YOUR_PLACEMENT_ID" : "1060491034146212_1060531607475488";

}
