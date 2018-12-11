package messenger.notificationsaver.notification.messenger.messengernotification.view.fragment.allNotifications;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import javax.inject.Inject;

import messenger.notificationsaver.notification.messenger.messengernotification.R;
import messenger.notificationsaver.notification.messenger.messengernotification.view.callbacks.ClickListener;
import messenger.notificationsaver.notification.messenger.messengernotification.view.callbacks.RecyclerTouchListener;
import messenger.notificationsaver.notification.messenger.messengernotification.view.fragment.base.BaseFragment;
import messenger.notificationsaver.notification.messenger.messengernotification.view.widgets.EmptyRecyclerView;

/**
 * Created by naimish on 10/12/2018
 */
public class AllNotificationsFragment extends BaseFragment implements ClickListener {

    EmptyRecyclerView recyclerView;
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
        recyclerView.setEmptyView(rootView.findViewById(R.id.empty_view));
        rvAdapter = new AllNotificationsAdapter();
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, this));

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        observeViewModel();
    }

    private void observeViewModel() {
        if (getActivity() == null) {
            return;
        }
        viewModel.getAppsWithNotifications().observe(getActivity(), rvAdapter::submitList);
    }

    @Override
    public void onClick(View view, int position) {

    }

    @Override
    public void onLongClick(View view, int position) {

    }
}
