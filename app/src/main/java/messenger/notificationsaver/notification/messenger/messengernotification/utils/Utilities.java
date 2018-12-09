package messenger.notificationsaver.notification.messenger.messengernotification.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

/**
 * Created by naimish on 07/12/2018
 */
public class Utilities {

    private static final String TAG = "Utilities";

    public void openPlayStoreToRate() {
        final String appPackageName;
        /*if (getActivity() != null) {
            appPackageName = getActivity().getPackageName();
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
            } catch (android.content.ActivityNotFoundException anfe) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
            }
        }*/
    }

    public static boolean isPieOrHigher() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.P;
    }

    public static boolean isOreoOrHigher() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O;
    }

    public static boolean isNougatOrHigher() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N;
    }

    public static boolean isMarshmallowOrHigher() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    public static String getAppNameFromPackage(Context context, String packageName) {
        PackageManager packageManager = context.getPackageManager();
        String appName = null;
        try {
            appName = (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA));
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "getAppNameFromPackage", e);
        }
        return appName;
    }

    public static Drawable getAppIconFromPackage(Context context, String packageName) {
        PackageManager packageManager = context.getPackageManager();
        Drawable appName = null;
        try {
            appName = packageManager.getApplicationIcon(packageName);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "getAppIconFromPackage", e);
        }
        return appName;
    }
}
