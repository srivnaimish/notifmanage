package messenger.notificationsaver.notification.messenger.messengernotification.view.activity.notificationTexts;

import android.arch.paging.PagedList;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import javax.inject.Inject;

import messenger.notificationsaver.notification.messenger.messengernotification.R;
import messenger.notificationsaver.notification.messenger.messengernotification.model.pojo.NotificationRow;
import messenger.notificationsaver.notification.messenger.messengernotification.utils.Constants;
import messenger.notificationsaver.notification.messenger.messengernotification.utils.Utilities;
import messenger.notificationsaver.notification.messenger.messengernotification.view.activity.base.BaseActivity;
import messenger.notificationsaver.notification.messenger.messengernotification.view.callbacks.ClickListener;
import messenger.notificationsaver.notification.messenger.messengernotification.view.callbacks.RecyclerTouchListener;
import messenger.notificationsaver.notification.messenger.messengernotification.view.widgets.EmptyRecyclerView;

/**
 * Created by naimish on 11/12/2018
 */
public class NotificationTextActivity extends BaseActivity implements ClickListener {

    EmptyRecyclerView recyclerView;
    NotificationTextAdapter rvAdapter;

    Toolbar toolbar;

    @Inject
    NotificationTextViewModel viewModel;
    private String appPackage, title;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_notifications;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerView = findViewById(R.id.notifications_rv);
        recyclerView.setEmptyView(findViewById(R.id.empty_view));
        rvAdapter = new NotificationTextAdapter();
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, this));
        recyclerView.setAdapter(rvAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        appPackage = getIntent().getStringExtra(Constants.PACKAGE_NAME);
        title = getIntent().getStringExtra(Constants.NOTIFICATION_TITLE);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        observeViewModel();
    }

    private void observeViewModel() {
        viewModel.getNotificationsTexts(appPackage, title).observe(this, rvAdapter::submitList);
    }

    @Override
    public void onClick(View view, int position) {
        PagedList<NotificationRow> allNotificationRows = rvAdapter.getCurrentList();
        if (Utilities.isEmpty(allNotificationRows)) {
            return;
        }

        NotificationRow row = allNotificationRows.get(rvAdapter.getRealPosition(position));
        if (row == null)
            return;

        Intent launchIntent = getPackageManager().getLaunchIntentForPackage(appPackage);
        startActivity(launchIntent);

    }

    @Override
    public void onLongClick(View view, int position) {

    }
}