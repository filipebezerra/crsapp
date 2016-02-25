package br.com.libertsolutions.crs.app.main;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import br.com.libertsolutions.crs.app.R;
import br.com.libertsolutions.crs.app.application.RequestCodes;
import br.com.libertsolutions.crs.app.android.activity.BaseActivity;
import br.com.libertsolutions.crs.app.feedback.FeedbackHelper;
import br.com.libertsolutions.crs.app.launchscreen.LaunchScreenActivity;
import br.com.libertsolutions.crs.app.login.LoginActivity;
import br.com.libertsolutions.crs.app.login.LoginHelper;
import br.com.libertsolutions.crs.app.work.WorkAdapter;
import br.com.libertsolutions.crs.app.android.recyclerview.DividerDecoration;
import br.com.libertsolutions.crs.app.android.recyclerview.OnClickListener;
import br.com.libertsolutions.crs.app.android.recyclerview.OnTouchListener;
import br.com.libertsolutions.crs.app.settings.SettingsActivity;
import br.com.libertsolutions.crs.app.settings.SettingsActivityCompat;
import br.com.libertsolutions.crs.app.settings.SettingsHelper;
import br.com.libertsolutions.crs.app.step.WorkStepActivity;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Application main screen.
 *
 * @author Filipe Bezerra
 * @version 0.1.0, 22/01/2016
 * @since 0.1.0
 */
public class MainActivity extends BaseActivity implements OnClickListener {

    private WorkAdapter mWorkAdapter;

    @Bind(R.id.root_view) protected CoordinatorLayout mRootView;

    @Override
    protected int provideLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle inState) {
        super.onCreate(inState);

        showLaunchScreen();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.title_activity_main);
        }

        RecyclerView mProjectsView = ButterKnife.findById(this, R.id.list);
        mProjectsView.setLayoutManager(new LinearLayoutManager(this));
        mProjectsView.setHasFixedSize(true);
        mProjectsView.setAdapter(
                mWorkAdapter = new WorkAdapter());
        mProjectsView.addItemDecoration(
                new DividerDecoration(this));
        mProjectsView.addOnItemTouchListener(
                new OnTouchListener(this, mProjectsView, this));
    }

    private void showLaunchScreen() {
        startActivityForResult(
                LaunchScreenActivity.getLauncherIntent(this),
                RequestCodes.LAUNCH_BRAND_SCREEN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case RequestCodes.LAUNCH_BRAND_SCREEN:
                if (!SettingsHelper.isSettingsApplied(this)) {
                    showSettingsScreen();
                } else if (!LoginHelper.isUserLogged(this)) {
                    showLoginScreen();
                } else {
                    showUserLoggedInfo();
                }
                break;

            case RequestCodes.LAUNCH_SETTINGS_SCREEN:
                if (!SettingsHelper.isSettingsApplied(this)) {
                    finish();
                }

                if (!LoginHelper.isUserLogged(this)) {
                    showLoginScreen();
                }
                break;

            case RequestCodes.LAUNCH_LOGIN_SCREEN:
                if (!LoginHelper.isUserLogged(this)) {
                    finish();
                } else {
                    showUserLoggedInfo();
                }
                break;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void showUserLoggedInfo() {
        final String userCpf = LoginHelper.getUserLogged(this);
        FeedbackHelper
                .snackbar(mRootView, String.format("Logado com cpf %s.", userCpf),
                        false);
    }

    private void showLoginScreen() {
        startActivityForResult(
                LoginActivity.getLauncherIntent(getApplicationContext()),
                RequestCodes.LAUNCH_LOGIN_SCREEN);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                LoginHelper.logoutUser(this);
                finish();
                return true;

            case R.id.action_settings:
                showSettingsScreen();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showSettingsScreen() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.GINGERBREAD_MR1) {
            startActivityForResult(
                    SettingsActivity.getLauncherIntent(getApplicationContext()),
                    RequestCodes.LAUNCH_SETTINGS_SCREEN);
        } else {
            startActivityForResult(
                    SettingsActivityCompat.getLauncherIntent(getApplicationContext()),
                    RequestCodes.LAUNCH_SETTINGS_SCREEN);
        }
    }

    @Override
    public void onSingleTapUp(View view, int position) {
        startActivity(WorkStepActivity.getLauncherIntent(this));
    }

    @Override
    public void onLongPress(View view, int position) {

    }
}
