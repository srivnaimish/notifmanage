package com.notification.core.view.fragment.newNotifications;

import android.arch.paging.PagedList;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import javax.inject.Inject;

import com.notification.core.R;
import com.notification.core.model.pojo.NotificationRow;
import com.notification.core.utils.IntentFactory;
import com.notification.core.utils.SharedPrefUtil;
import com.notification.core.utils.Utilities;
import com.notification.core.view.callbacks.ClickListener;
import com.notification.core.view.callbacks.RecyclerTouchListener;
import com.notification.core.view.fragment.allNotifications.AllNotificationsAdapter;
import com.notification.core.view.fragment.base.BaseFragment;

/**
 * Created by naimish on 10/12/2018
 */
public class NewNotificationsFragment extends BaseFragment implements ClickListener {

    @Inject
    SharedPrefUtil sharedPrefUtil;
    RecyclerView recyclerView;

    @Inject
    AllNotificationsAdapter rvAdapter;
    View emptyView;

    @Inject
    NewNotificationsViewModel viewModel;

    public static NewNotificationsFragment newInstance() {
        return new NewNotificationsFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_new_notifications;
    }

    @Override
    protected void onBindView(View rootView, Bundle savedInstanceState) {
        recyclerView = rootView.findViewById(R.id.notifications_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(rvAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, this));
        emptyView = findViewById(R.id.empty_view);
        observeViewModel();
    }

    private void observeViewModel() {
        if (getActivity() == null) {
            return;
        }
        long time = sharedPrefUtil.getLastSessionTime();
        if (time == 0) {
            emptyView.setVisibility(View.VISIBLE);
            return;
        }
        viewModel.getNewNotificationApps(time).observe(getActivity(), list -> {
            if (Utilities.isEmpty(list)) {
                emptyView.setVisibility(View.VISIBLE);
            } else {
                emptyView.setVisibility(View.GONE);
            }
            rvAdapter.submitList(list);
        });
    }

    @Override
    public void onClick(View view, int position) {
        PagedList<NotificationRow> allNotificationRows = rvAdapter.getCurrentList();
        if (Utilities.isEmpty(allNotificationRows)) {
            return;
        }

        NotificationRow row = allNotificationRows.get(position);
        if (row == null)
            return;

        startActivity(IntentFactory.getTitleWiseActivity(getContext(), row.getAppPackage()));
    }

    @Override
    public void onLongClick(View view, int position) {

    }
}
