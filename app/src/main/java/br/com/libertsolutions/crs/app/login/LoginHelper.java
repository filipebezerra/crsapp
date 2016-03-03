package br.com.libertsolutions.crs.app.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.content.SharedPreferencesCompat;
import android.text.TextUtils;

/**
 * Utilities methods used in {@link LoginActivity}.
 *
 * @author Filipe Bezerra
 * @version 0.1.0, 22/01/2016
 * @since 0.1.0
 */
public class LoginHelper {
    private static final String KEY_IS_USER_LOGGED = "isUserLogged";
    private static final String KEY_USER_CPF = "cpf";

    private LoginHelper() {
        // no constructor
    }

    public static boolean isUserLogged(@NonNull Context context) {
        final SharedPreferences sharedPreferences
                = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getBoolean(KEY_IS_USER_LOGGED, false);
    }

    public static void loginUser(@NonNull Context context, @NonNull String cpf) {
        if (isUserLogged(context))
            return;

        final SharedPreferences sharedPreferences
                = PreferenceManager.getDefaultSharedPreferences(context);

        SharedPreferencesCompat.EditorCompat
                .getInstance()
                .apply(
                        sharedPreferences.edit()
                                .putBoolean(KEY_IS_USER_LOGGED, true)
                                .putString(KEY_USER_CPF, cpf));
    }

    public static String getUserLogged(@NonNull Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(KEY_USER_CPF, null);
    }

    public static void logoutUser(@NonNull Context context) {
        if (!isUserLogged(context))
            return;

        final SharedPreferences sharedPreferences
                = PreferenceManager.getDefaultSharedPreferences(context);

        SharedPreferencesCompat.EditorCompat
                .getInstance()
                .apply(
                        sharedPreferences.edit()
                                .putBoolean(KEY_IS_USER_LOGGED, false)
                                .remove(KEY_USER_CPF));
    }

    public static String formatCpf(@NonNull String cpf) {
        if (TextUtils.isEmpty(cpf) || cpf.length() != 11) {
            return cpf;
        }

        return new StringBuilder(cpf)
                .insert(3, ".")
                .insert(7, ".")
                .insert(11, "-")
                .toString();
    }
}
