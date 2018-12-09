package messenger.notificationsaver.notification.messenger.messengernotification.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import javax.inject.Inject;

import messenger.notificationsaver.notification.messenger.messengernotification.model.dagger.qualifiers.ApplicationContext;
import messenger.notificationsaver.notification.messenger.messengernotification.utils.Constants;

/**
 * Created by naimish on 07/12/2018
 */
public class SharedPrefUtil {

    private SharedPreferences.Editor editor;
    private SharedPreferences sharedpreferences;

    @Inject
    public SharedPrefUtil(@ApplicationContext Context context) {
        sharedpreferences = context.getSharedPreferences("messenger_notification", Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
    }

    public boolean putBoolean(String key, Boolean value) {
        editor.putBoolean(key, value);
        return editor.commit();
    }

    public boolean putLong(String key, Long value) {
        editor.putLong(key, value);
        return editor.commit();
    }

    private boolean putString(String key, String value) {
        editor.putString(key, value);
        return editor.commit();
    }

    private boolean putInt(String key, int value) {
        editor.putInt(key, value);
        return editor.commit();
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return sharedpreferences.getBoolean(key, defaultValue);
    }

    public Long getLong(String key, Long defaultValue) {
        return sharedpreferences.getLong(key, defaultValue);
    }

    public String getString(String key, String defaultValue) {
        return sharedpreferences.getString(key, defaultValue);
    }

    public int getInt(String key, int defaultValue) {
        return sharedpreferences.getInt(key, defaultValue);
    }

    public boolean hasSeenOnBoarding() {
        return getBoolean(Constants.SEEN_ONBOARDING, false);
    }

    public void setSeenOnBoarding(boolean value) {
        putBoolean(Constants.SEEN_ONBOARDING, value);
    }

    public boolean isAutoStartEnabled() {
        return getBoolean(Constants.AUTO_START, false);
    }

    public void setAutoStartEnabled() {
        putBoolean(Constants.AUTO_START, true);
    }

    public boolean isBatteryOptimizationDisabled() {
        return getBoolean(Constants.BATTERY_OPTIMIZATION, false);
    }

    public void setBatteryOptimizationDisabled() {
        putBoolean(Constants.BATTERY_OPTIMIZATION, true);
    }

    public void saveInstalledApps(JSONArray installedAppsList) {
        if (Utilities.isEmpty(installedAppsList)) {
            return;
        }
        putString(Constants.INSTALLED_APPS_PACKAGES, installedAppsList.toString());
    }

    public JSONArray getInstalledApps() {
        JSONArray apps = null;
        try {
            apps = new JSONArray(getString(Constants.INSTALLED_APPS_PACKAGES, null));
        } catch (JSONException e) {
            Log.e(getClass().getSimpleName(), "getInstalledApps", e);
        }
        return apps;
    }
}
