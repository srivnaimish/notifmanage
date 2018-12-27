package com.notification.core.view.activity.titleWise;

import android.arch.paging.PagedList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import javax.inject.Inject;

import com.notification.core.R;
import com.notification.core.model.pojo.NotificationRow;
import com.notification.core.utils.Constants;
import com.notification.core.utils.DividerItemDecorator;
import com.notification.core.utils.IntentFactory;
import com.notification.core.utils.Utilities;
import com.notification.core.view.activity.base.BaseActivity;
import com.notification.core.view.callbacks.ClickListener;
import com.notification.core.view.callbacks.RecyclerTouchListener;

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
        recyclerView.setItemAnimator(null);
        recyclerView.addItemDecoration(new DividerItemDecorator(ContextCompat.getDrawable(context, R.drawable.divider)));
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

        startActivity(IntentFactory.getNotificationTextActivity(this, row.getAppPackage(), row.getTitle(), row.getTag()));
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