package br.com.libertsolutions.crs.app.presentation.settings;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import br.com.libertsolutions.crs.app.R;

/**
 * @author Filipe Bezerra
 * @since 0.1.0
 */
@TargetApi(Build.VERSION_CODES.GINGERBREAD_MR1)
public class SettingsActivityCompat extends android.preference.PreferenceActivity {

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
