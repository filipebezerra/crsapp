package br.com.libertsolutions.crs.app.data.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import br.com.libertsolutions.crs.app.presentation.settings.SettingsActivity;
import br.com.libertsolutions.crs.app.presentation.settings.SettingsActivityCompat;

/**
 * Utilities methods used in {@link SettingsActivity} or {@link SettingsActivityCompat}.
 *
 * @author Filipe Bezerra
 * @since 0.1.0
 */
public class SettingsDataHelper {
    private static final String KEY_APPLIED_ON_FIRST_RUN = "applied_on_first_run";
    public static final String PREF_SERVER_URL = "server_url";
    public static final String PREF_SERVER_AUTH_KEY = "server_auth_key";

    private SettingsDataHelper() {
        // no constructor
    }

    public static boolean isSettingsApplied(@NonNull Context context) {
        final SharedPreferences sharedPreferences
                = PreferenceManager.getDefaultSharedPreferences(context);

        return (sharedPreferences.getString(PREF_SERVER_URL, null) != null)
                && (sharedPreferences.getString(PREF_SERVER_AUTH_KEY, null) != null);
    }

    public static String getServerUrl(@NonNull  Context context) {
        final SharedPreferences preferences
                = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(PREF_SERVER_URL, null);
    }

    public static String getServerAuthKey(@NonNull Context context) {
        final SharedPreferences preferences
                = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(PREF_SERVER_AUTH_KEY, null);
    }
}
