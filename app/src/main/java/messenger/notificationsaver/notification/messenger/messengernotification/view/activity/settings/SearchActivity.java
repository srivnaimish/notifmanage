package messenger.notificationsaver.notification.messenger.messengernotification.view.activity.settings;

import android.arch.paging.PagedList;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import javax.inject.Inject;

import messenger.notificationsaver.notification.messenger.messengernotification.R;
import messenger.notificationsaver.notification.messenger.messengernotification.model.pojo.SearchRow;
import messenger.notificationsaver.notification.messenger.messengernotification.utils.Constants;
import messenger.notificationsaver.notification.messenger.messengernotification.utils.IntentFactory;
import messenger.notificationsaver.notification.messenger.messengernotification.utils.Utilities;
import messenger.notificationsaver.notification.messenger.messengernotification.view.activity.base.BaseActivity;
import messenger.notificationsaver.notification.messenger.messengernotification.view.activity.notificationTexts.NotificationTextAdapter;
import messenger.notificationsaver.notification.messenger.messengernotification.view.activity.notificationTexts.NotificationTextViewModel;
import messenger.notificationsaver.notification.messenger.messengernotification.view.callbacks.ClickListener;
import messenger.notificationsaver.notification.messenger.messengernotification.view.callbacks.RecyclerTouchListener;

/**
 * Created by naimish on 11/12/2018
 */
public class SearchActivity extends BaseActivity implements ClickListener, SearchView.OnQueryTextListener {

    RecyclerView recyclerView;

    ImageButton search;
    @Inject
    SearchAdapter rvAdapter;

    @Inject
    SearchViewModel viewModel;

    SearchView searchView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerView = findViewById(R.id.notifications_rv);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, this));
        recyclerView.setAdapter(rvAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        searchView = findViewById(R.id.search_view);
        search = findViewById(R.id.search);
        bindClick(R.id.search, v -> onQueryTextSubmit(searchView.getQuery().toString()));
        searchView.setOnQueryTextListener(this);
        searchView.requestFocus();
    }

    private void observeViewModel(String query) {
        viewModel.getSearchResults(query).observe(this, list -> {
            rvAdapter.submitList(list);
            if (Utilities.isEmpty(list)) {
                showSnackBar("Nothing Found");
            }
        });
    }

    @Override
    public void onClick(View view, int position) {
        PagedList<SearchRow> allSearchRows = rvAdapter.getCurrentList();
        if (Utilities.isEmpty(allSearchRows)) {
            return;
        }

        SearchRow row = allSearchRows.get(rvAdapter.getRealPosition(position));
        if (row == null)
            return;

        startActivity(IntentFactory.getNotificationTextActivity(this, row.getAppPackage(), row.getTitle()));
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    public void onLongClick(View view, int position) {

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        query = query.trim();
        if (query.length() > 3) {
            query = "%" + query + "%";
            observeViewModel(query);
            searchView.clearFocus();
        }
        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }
}