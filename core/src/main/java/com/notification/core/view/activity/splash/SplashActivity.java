package com.notification.core.view.activity.splash;

import android.os.Bundle;

import javax.inject.Inject;

import com.notification.core.utils.SharedPrefUtil;
import com.notification.core.utils.IntentFactory;
import com.notification.core.utils.Utilities;
import com.notification.core.view.activity.base.BaseActivityView;

public class SplashActivity extends BaseActivityView<SplashContract.Presenter> implements SplashContract.View {


    @Inject
    SharedPrefUtil sharedPrefUtil;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (sharedPrefUtil.hasSeenOnBoarding()) {
            startActivity(IntentFactory.getLandingActivity(SplashActivity.this));
            finish();
            return;
        }
        startActivity(IntentFactory.getOnboardingActivity(SplashActivity.this));
        finish();
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }
}