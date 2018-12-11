package messenger.notificationsaver.notification.messenger.messengernotification.view.activity.appWiseNotifications;

import android.arch.paging.PagedList;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import javax.inject.Inject;

import messenger.notificationsaver.notification.messenger.messengernotification.R;
import messenger.notificationsaver.notification.messenger.messengernotification.model.pojo.NotificationRow;
import messenger.notificationsaver.notification.messenger.messengernotification.utils.Utilities;
import messenger.notificationsaver.notification.messenger.messengernotification.view.callbacks.ClickListener;
import messenger.notificationsaver.notification.messenger.messengernotification.view.callbacks.RecyclerTouchListener;
import messenger.notificationsaver.notification.messenger.messengernotification.view.fragment.base.BaseFragment;
import messenger.notificationsaver.notification.messenger.messengernotification.view.widgets.EmptyRecyclerView;

/**
 * Created by naimish on 11/12/2018
 */
public class AppWiseFragment extends BaseFragment implements ClickListener {

    EmptyRecyclerView recyclerView;
    AppWiseAdapter rvAdapter;

    @Inject
    AppWiseViewModel viewModel;

    public static AppWiseFragment newInstance() {
        return new AppWiseFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_appwise_notifications;
    }

    @Override
    protected void onBindView(View rootView, Bundle savedInstanceState) {
        recyclerView = rootView.findViewById(R.id.notifications_rv);
        recyclerView.setEmptyView(rootView.findViewById(R.id.empty_view));
        rvAdapter = new AppWiseAdapter();
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, this));

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        observeViewModel();
    }

    private void observeViewModel() {
        if (getActivity() == null) {
            return;
        }
        viewModel.getAppWiseNotifications().observe(getActivity(), rvAdapter::submitList);
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

        //open(AppWiseFragment.getInstance(row.getAppPackage, row.getTitle))
    }

    @Override
    public void onLongClick(View view, int position) {

    }
}