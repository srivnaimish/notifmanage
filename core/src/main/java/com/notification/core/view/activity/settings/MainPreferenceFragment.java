package com.notification.core.view.activity.settings;

import android.app.AlertDialog;
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

        Preference preference = findPreference("delete_notifications");
        preference.setOnPreferenceClickListener(preference12 -> {
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