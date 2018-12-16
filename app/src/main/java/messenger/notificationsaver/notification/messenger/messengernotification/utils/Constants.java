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

    public static final int AD_REPEAT_POSITION = 5;
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

    public static final String DEFAULT_CHAT_KEY = "default_chat_key";
    public static final String DEFAULT_CHAT_KEY_VALUE = "com.google.android.apps.fireball,reply;com.google.android.talk,android.intent.extra.TEXT;com.skype.raider,key_text_reply;com.viber.voip,remote_text_input;jp.naver.line.android,line.text;org.telegram.messenger,extra_voice_reply;com.instagram.android,DirectNotificationConstants.DirectReply;com.twitter.android,dm_text;com.facebook.orca,voice_reply;com.kakao.talk,extra_voice_reply,extra_direct_reply;com.whatsapp,android_wear_voice_input;com.Slack,key_reply_text;com.samsung.android.messaging,key_reply_text";
    public static final String DEFAULT_CHAT_VALUE = "com.snapchat.android;com.google.android.talk;com.google.android.apps.fireball;com.Slack;com.yahoo.mobile.client.android.im;com.viber.voip;com.whatsapp;com.facebook.mlite;com.facebook.orca;org.telegram.messenger;jp.naver.line.android;com.linecorp.linelite;com.kakao.talk;com.tencent.mm";

    public static final String LAST_SESSION = "last_session";

    public static final String ADMOB_ACCOUNT = "ca-app-pub-3940256099942544~3347511713";
    public static final String ADMOB_BANNER = "ca-app-pub-3463246254277621/7281189803";
    public static final String ADMOB_INTERSTITIAL = "ca-app-pub-3463246254277621/7252327690";
    public static final String ADMOB_REWARDS = "ca-app-pub-3463246254277621/4734917514";
    public static final String ADMOB_NATIVE = "ca-app-pub-3940256099942544/2247696110";

}
