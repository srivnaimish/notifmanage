package messenger.notificationsaver.notification.messenger.messengernotification.view.activity.splash;

import android.os.Bundle;

import org.json.JSONArray;

import javax.inject.Inject;

import messenger.notificationsaver.notification.messenger.messengernotification.utils.SharedPrefUtil;
import messenger.notificationsaver.notification.messenger.messengernotification.utils.IntentFactory;
import messenger.notificationsaver.notification.messenger.messengernotification.utils.Utilities;
import messenger.notificationsaver.notification.messenger.messengernotification.view.activity.base.BaseActivityView;

public class SplashActivity extends BaseActivityView<SplashContract.Presenter> implements SplashContract.View {


    @Inject
    SharedPrefUtil sharedPrefUtil;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter.saveInstalledApps();

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