package messenger.notificationsaver.notification.messenger.messengernotification.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public static boolean isInstalledPackage(Context context, String packageName) {
        PackageManager pm = context.getPackageManager();
        if (pm == null) {
            return false;
        }

        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> allApps = pm.queryIntentActivities(intent, 0);

        for (ResolveInfo ri : allApps) {
            if (ri.activityInfo.packageName.equalsIgnoreCase(packageName)) {
                return true;
            }
        }
        return false;
    }

    public static <S, T extends Iterable<S>> boolean isEmpty(T argument) {
        return (argument == null) || !argument.iterator().hasNext();
    }

    public static <T extends Object> boolean isEmpty(T[] argument) {
        return (argument == null) || argument.length == 0;
    }

    public static <T extends Map> boolean isEmpty(T argument) {
        return (argument == null) || argument.size() == 0;
    }

    public static boolean isEmpty(JSONArray jsonArray) {
        return (jsonArray == null) || (jsonArray.length() == 0);
    }

    public static boolean isEmpty(JSONObject jsonObject) {
        return (jsonObject == null) || (jsonObject.length() == 0);
    }

    public static boolean isEmpty(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            return true;
        }
        return charSequence.toString().equalsIgnoreCase("null");
    }

    public static void loadImageIntoView(Context context, ImageView imageView, String url) {
        Glide.with(context)
                .load(url)
                .into(imageView);
    }
}
