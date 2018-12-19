package messenger.notificationsaver.notification.messenger.messengernotification.view.activity.textWise;

import android.arch.paging.PagedList;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import javax.inject.Inject;

import messenger.notificationsaver.notification.messenger.messengernotification.R;
import messenger.notificationsaver.notification.messenger.messengernotification.model.pojo.NotificationRow;
import messenger.notificationsaver.notification.messenger.messengernotification.utils.Constants;
import messenger.notificationsaver.notification.messenger.messengernotification.utils.Utilities;
import messenger.notificationsaver.notification.messenger.messengernotification.view.activity.base.BaseActivity;
import messenger.notificationsaver.notification.messenger.messengernotification.view.callbacks.ClickListener;
import messenger.notificationsaver.notification.messenger.messengernotification.view.callbacks.RecyclerTouchListener;

/**
 * Created by naimish on 11/12/2018
 */
public class NotificationTextActivity extends BaseActivity implements ClickListener {

    RecyclerView recyclerView;
    NotificationTextAdapter rvAdapter;

    Toolbar toolbar;

    @Inject
    NotificationTextViewModel viewModel;
    private String appPackage, title;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_detailed_notifications;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerView = findViewById(R.id.notifications_rv);
        rvAdapter = new NotificationTextAdapter();
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, this));
        recyclerView.setAdapter(rvAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);

        appPackage = getIntent().getStringExtra(Constants.PACKAGE_NAME);
        title = getIntent().getStringExtra(Constants.NOTIFICATION_TITLE);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        rvAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                if (positionStart == 0) {
                    layoutManager.scrollToPosition(0);
                }
            }
        });

        observeViewModel();
    }

    private void observeViewModel() {
        viewModel.getNotificationsTexts(appPackage, title).observe(this, list -> rvAdapter.submitList(list));
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

    @Override
    protected void onDestroy() {
        viewModel.updateStatus(appPackage, title);
        super.onDestroy();
    }
}