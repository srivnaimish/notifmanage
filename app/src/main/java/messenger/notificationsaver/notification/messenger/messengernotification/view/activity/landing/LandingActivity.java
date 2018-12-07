package messenger.notificationsaver.notification.messenger.messengernotification.view.activity.landing;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import messenger.notificationsaver.notification.messenger.messengernotification.R;
import messenger.notificationsaver.notification.messenger.messengernotification.utils.IntentFactory;
import messenger.notificationsaver.notification.messenger.messengernotification.view.activity.base.BaseActivityView;

/**
 * Created by naimish on 07/12/2018
 */
public class LandingActivity extends BaseActivityView<LandingContract.Presenter> implements LandingContract.View {

    Button notificationAccess;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_landing;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notificationAccess = findViewById(R.id.notification_access);
        bindClick(notificationAccess.getId(), v -> startActivity(IntentFactory.getNotificationAccessSetting()));
    }

    @Override
    public void requestNotificationAccess() {
        notificationAccess.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        notificationAccess.setVisibility(View.GONE);
        presenter.checkNotificationAccess(LandingActivity.this);
    }
}
