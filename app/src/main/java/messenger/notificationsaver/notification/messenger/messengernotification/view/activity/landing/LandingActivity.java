package messenger.notificationsaver.notification.messenger.messengernotification.view.activity.landing;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import javax.inject.Inject;

import messenger.notificationsaver.notification.messenger.messengernotification.R;
import messenger.notificationsaver.notification.messenger.messengernotification.model.notifications.AppNotifications;
import messenger.notificationsaver.notification.messenger.messengernotification.model.room.dao.NotificationDao;
import messenger.notificationsaver.notification.messenger.messengernotification.utils.IntentFactory;
import messenger.notificationsaver.notification.messenger.messengernotification.view.activity.base.BaseActivityView;

/**
 * Created by naimish on 07/12/2018
 */
public class LandingActivity extends BaseActivityView<LandingContract.Presenter> implements LandingContract.View, ViewPager.OnPageChangeListener {

    private ViewPager viewPager;
    private Toolbar toolbar;
    private LandingPagerAdapter landingPagerAdapter;
    private BottomNavigationView bottomNavigationView;
    private MenuItem prevMenuItem;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_landing;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewPager = findViewById(R.id.viewpager);
        toolbar = findViewById(R.id.toolbar);

        landingPagerAdapter = new LandingPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(landingPagerAdapter);
        viewPager.addOnPageChangeListener(this);

        bottomNavigationView = findViewById(R.id.landing_tabs);
        setUpBottomNavigation();
    }

    private void setUpBottomNavigation() {
        bottomNavigationView.setOnNavigationItemSelectedListener(
                item -> {
                    switch (item.getItemId()) {
                        case R.id.all:
                            openPage(0);
                            break;
                    }
                    return false;
                });
    }

    private void openPage(int pos) {
        if (pos == viewPager.getCurrentItem()) {
            return;
        }
        viewPager.setCurrentItem(pos, true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!presenter.hasNotificationAccess(LandingActivity.this)) {
            return;
        }

        if (!presenter.isBatterySaverDisabled()) {  //For doze mode disable
            return;
        }

        if (!presenter.isAutoStartEnabled()) {  //For mi,oppo,etc devices
            return;
        }

    }

    @Override
    public void requestNotificationAccess() {
        new AlertDialog.Builder(this)
                .setTitle("Access Notifications")
                .setMessage("Allow Access to Phone Notifications")
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                    startActivity(IntentFactory.getNotificationAccessSetting());
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                })
                .show();
    }

    @Override
    public void requestAutoStartPermission() {
        for (Intent intent : IntentFactory.AUTO_START_INTENTS) {
            if (getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY) != null) {
                new AlertDialog.Builder(this)
                        .setTitle("Auto Start")
                        .setMessage("Enable Auto Start to access notifications in background")
                        .setPositiveButton("Settings", (dialog, which) -> {
                            sharedPrefUtil.setAutoStartEnabled();
                            startActivity(intent);
                            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        })
                        .show();
                return;
            }
        }
        sharedPrefUtil.setAutoStartEnabled();
    }

    @Override
    public void requestDisableBatteryOptimization() {
        new AlertDialog.Builder(this)
                .setTitle("Battery Optimization")
                .setMessage("Turn of Battery Optimization to allow seamless notification detection")
                .setPositiveButton("Turn off", (dialog, which) -> {
                    sharedPrefUtil.setBatteryOptimizationDisabled();
                    startActivity(IntentFactory.getBatteryOptimizationIntent(LandingActivity.this));
                })
                .show();
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int position) {
        if (prevMenuItem != null) {
            prevMenuItem.setChecked(false);
        } else {
            bottomNavigationView.getMenu().getItem(0).setChecked(false);
        }
        bottomNavigationView.getMenu().getItem(position).setChecked(true);
        prevMenuItem = bottomNavigationView.getMenu().getItem(position);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}
