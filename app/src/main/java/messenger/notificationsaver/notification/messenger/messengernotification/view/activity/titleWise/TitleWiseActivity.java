package messenger.notificationsaver.notification.messenger.messengernotification.view.activity.titleWise;

import android.arch.paging.PagedList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import javax.inject.Inject;

import messenger.notificationsaver.notification.messenger.messengernotification.R;
import messenger.notificationsaver.notification.messenger.messengernotification.model.pojo.NotificationRow;
import messenger.notificationsaver.notification.messenger.messengernotification.utils.Constants;
import messenger.notificationsaver.notification.messenger.messengernotification.utils.IntentFactory;
import messenger.notificationsaver.notification.messenger.messengernotification.utils.Utilities;
import messenger.notificationsaver.notification.messenger.messengernotification.view.activity.base.BaseActivity;
import messenger.notificationsaver.notification.messenger.messengernotification.view.callbacks.ClickListener;
import messenger.notificationsaver.notification.messenger.messengernotification.view.callbacks.RecyclerTouchListener;

/**
 * Created by naimish on 11/12/2018
 */
public class TitleWiseActivity extends BaseActivity implements ClickListener {

    RecyclerView recyclerView;

    @Inject
    TitleWiseAdapter rvAdapter;

    @Inject
    TitleWiseViewModel viewModel;
    private String appPackage;
    private Toolbar toolbar;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_title_notifications;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerView = findViewById(R.id.notifications_rv);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, this));
        recyclerView.setAdapter(rvAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        appPackage = getIntent().getStringExtra(Constants.PACKAGE_NAME);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(Utilities.getAppNameFromPackage(this, appPackage));
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        observeViewModel();
    }

    private void observeViewModel() {
        viewModel.getAppWiseNotifications(appPackage).observe(this, list -> {
            rvAdapter.submitList(list);
        });
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

        startActivity(IntentFactory.getNotificationTextActivity(this, row.getAppPackage(), row.getTitle()));
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    public void onLongClick(View view, int position) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}