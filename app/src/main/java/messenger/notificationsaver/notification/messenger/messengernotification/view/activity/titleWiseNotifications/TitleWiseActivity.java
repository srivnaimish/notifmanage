package messenger.notificationsaver.notification.messenger.messengernotification.view.activity.titleWiseNotifications;

import android.arch.paging.PagedList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import javax.inject.Inject;

import messenger.notificationsaver.notification.messenger.messengernotification.R;
import messenger.notificationsaver.notification.messenger.messengernotification.model.pojo.NotificationRow;
import messenger.notificationsaver.notification.messenger.messengernotification.utils.Constants;
import messenger.notificationsaver.notification.messenger.messengernotification.utils.IntentFactory;
import messenger.notificationsaver.notification.messenger.messengernotification.utils.Utilities;
import messenger.notificationsaver.notification.messenger.messengernotification.view.activity.base.BaseActivity;
import messenger.notificationsaver.notification.messenger.messengernotification.view.activity.base.BaseActivityView;
import messenger.notificationsaver.notification.messenger.messengernotification.view.callbacks.ClickListener;
import messenger.notificationsaver.notification.messenger.messengernotification.view.callbacks.RecyclerTouchListener;
import messenger.notificationsaver.notification.messenger.messengernotification.view.widgets.EmptyRecyclerView;

/**
 * Created by naimish on 11/12/2018
 */
public class TitleWiseActivity extends BaseActivity implements ClickListener {

    EmptyRecyclerView recyclerView;
    TitleWiseAdapter rvAdapter;

    @Inject
    TitleWiseViewModel viewModel;
    private String appPackage;

    public static TitleWiseActivity newInstance() {
        return new TitleWiseActivity();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_titlewise_notifications;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerView = findViewById(R.id.notifications_rv);
        recyclerView.setEmptyView(findViewById(R.id.empty_view));
        rvAdapter = new TitleWiseAdapter();
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, this));
        recyclerView.setAdapter(rvAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        appPackage = getIntent().getStringExtra(Constants.PACKAGE_NAME);

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
    }

    @Override
    public void onLongClick(View view, int position) {

    }

    @Override
    protected void onDestroy() {
        viewModel.markAppNotificationsRead(appPackage);
        super.onDestroy();
    }
}