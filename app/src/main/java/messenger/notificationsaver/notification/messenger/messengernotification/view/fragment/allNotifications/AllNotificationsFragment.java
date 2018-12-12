package messenger.notificationsaver.notification.messenger.messengernotification.view.fragment.allNotifications;

import android.arch.paging.PagedList;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import javax.inject.Inject;

import messenger.notificationsaver.notification.messenger.messengernotification.R;
import messenger.notificationsaver.notification.messenger.messengernotification.model.pojo.NotificationRow;
import messenger.notificationsaver.notification.messenger.messengernotification.utils.IntentFactory;
import messenger.notificationsaver.notification.messenger.messengernotification.utils.Utilities;
import messenger.notificationsaver.notification.messenger.messengernotification.view.callbacks.ClickListener;
import messenger.notificationsaver.notification.messenger.messengernotification.view.callbacks.RecyclerTouchListener;
import messenger.notificationsaver.notification.messenger.messengernotification.view.fragment.base.BaseFragment;

/**
 * Created by naimish on 10/12/2018
 */
public class AllNotificationsFragment extends BaseFragment implements ClickListener {

    RecyclerView recyclerView;
    AllNotificationsAdapter rvAdapter;

    @Inject
    AllNotificationsViewModel viewModel;

    public static AllNotificationsFragment newInstance() {
        return new AllNotificationsFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_all_notifications;
    }

    @Override
    protected void onBindView(View rootView, Bundle savedInstanceState) {
        recyclerView = rootView.findViewById(R.id.notifications_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        rvAdapter = new AllNotificationsAdapter();
        recyclerView.setAdapter(rvAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, this));
        observeViewModel();
    }

    private void observeViewModel() {
        if (getActivity() == null) {
            return;
        }
        viewModel.getAppsWithNotifications().observe(getActivity(), list -> {
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

        startActivity(IntentFactory.getTitleWiseActivity(getContext(), row.getAppPackage()));
    }

    @Override
    public void onLongClick(View view, int position) {

    }
}
