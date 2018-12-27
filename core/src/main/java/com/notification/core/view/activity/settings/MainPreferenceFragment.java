package com.notification.core.view.activity.settings;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;

import com.notification.core.R;
import com.notification.core.model.notifications.AppNotifications;
import com.notification.core.utils.Constants;

/**
 * Created by naimish on 17/12/2018
 */
public class MainPreferenceFragment extends PreferenceFragment {

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preference);

        //bindPreferenceSummaryToValue(findPreference("keep_notifications"));

        findPreference("delete_notifications").setOnPreferenceClickListener(preference12 -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                    .setTitle("Are you sure?")
                    .setMessage("This operation cannot be undone")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        if (getActivity() != null)
                            ((SettingsActivity) getActivity()).deleteAllNotifications();
                    })
                    .setNegativeButton("No", (dialog, which) -> {
                        dialog.dismiss();
                    });
            builder.show();
            return false;
        });

        SwitchPreference switchPreference = (SwitchPreference) findPreference(Constants.SHOW_NOTIFICATION);
        switchPreference.setOnPreferenceChangeListener((preference1, newValue) -> {
            if (!(boolean) newValue) {
                AppNotifications.removeNotification(getActivity());
            }
            return true;
        });

        findPreference("share").setOnPreferenceClickListener(preference12 -> {
            String message = "Manage all your notifications in a single place. Download Notikeep at " + Constants.APP_URL;
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, message);
            startActivity(Intent.createChooser(shareIntent, "Invite via.."));
            return false;
        });

        findPreference("rate").setOnPreferenceClickListener(preference12 -> {
            final String appPackageName = getActivity().getPackageName();
            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=" + appPackageName));
            if (intent.resolveActivity(getActivity().getPackageManager()) == null) {
                intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id="
                                + appPackageName));
            }
            startActivity(intent);
            return false;
        });
    }

    /*private static void bindPreferenceSummaryToValue(Preference preference) {
        preference.setOnPreferenceChangeListener(sBindPreferenceSummaryToValueListener);

        sBindPreferenceSummaryToValueListener.onPreferenceChange(preference,
                PreferenceManager
                        .getDefaultSharedPreferences(preference.getContext())
                        .getString(preference.getKey(), ""));
    }

    private static Preference.OnPreferenceChangeListener sBindPreferenceSummaryToValueListener = new Preference.OnPreferenceChangeListener() {
        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            String stringValue = newValue.toString();

            if (preference instanceof ListPreference) {
                // For list preferences, look up the correct display value in
                // the preference's 'entries' list.
                ListPreference listPreference = (ListPreference) preference;
                int index = listPreference.findIndexOfValue(stringValue);

                // Set the summary to reflect the new value.
                preference.setSummary(
                        index >= 0
                                ? listPreference.getEntries()[index]
                                : null);

            } else {
                preference.setSummary(stringValue);
            }
            return true;
        }
    };*/
}