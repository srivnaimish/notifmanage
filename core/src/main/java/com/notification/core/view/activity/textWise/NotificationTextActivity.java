package com.notification.core.view.activity.textWise;

import android.app.Notification;
import android.arch.paging.PagedList;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import com.notification.core.R;
import com.notification.core.model.pojo.NotificationRow;
import com.notification.core.model.pojo.ReplyIntentSender;
import com.notification.core.utils.Constants;
import com.notification.core.utils.Utilities;
import com.notification.core.view.activity.base.BaseActivity;
import com.notification.core.view.callbacks.ClickListener;
import com.notification.core.view.callbacks.RecyclerTouchListener;

/**
 * Created by naimish on 11/12/2018
 */
public class NotificationTextActivity extends BaseActivity implements ClickListener {

    RecyclerView recyclerView;
    NotificationTextAdapter rvAdapter;

    Toolbar toolbar;
    ImageView appIcon;
    Notification.Action replyAction;

    @Inject
    NotificationTextViewModel viewModel;
    private String appPackage, title, tag;
    private EditText messageText;
    private View chatView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_detailed_notifications;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        appPackage = getIntent().getStringExtra(Constants.PACKAGE_NAME);
        title = getIntent().getStringExtra(Constants.NOTIFICATION_TITLE);
        tag = getIntent().getStringExtra(Constants.NOTIFICATION_TAG);

        recyclerView = findViewById(R.id.notifications_rv);
        messageText = findViewById(R.id.message_text);
        chatView = findViewById(R.id.bottom_card);
        appIcon = findViewById(R.id.app_icon);

        rvAdapter = new NotificationTextAdapter();
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, this));
        recyclerView.setAdapter(rvAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        if (Constants.DEFAULT_CHAT_VALUE.contains(appPackage)) {
            layoutManager.setReverseLayout(true);
            layoutManager.scrollToPosition(0);
        }

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(null);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        appIcon.setImageDrawable(Utilities.getAppIconFromPackage(context, appPackage));

        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        rvAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                if (positionStart == 0) {
                    layoutManager.scrollToPosition(0);
                }
            }
        });

        checkViewType();

        observeViewModel();

        bindClick(R.id.send, v -> sendMessage());

        loadBannerAd();
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

        NotificationRow row = allNotificationRows.get(position);
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

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);

    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    private void checkViewType() {
        Intent localIntent = new Intent(Constants.SERVICE_REQUEST_ACTION);  //Communicate with service
        localIntent.putExtra(Constants.NOTIFICATION_TAG, tag);
        sendBroadcast(localIntent);
    }

    @Subscribe
    public void onEvent(Notification.Action action) {
        if (action == null) {
            return;
        }

        replyAction = action;
        chatView.setVisibility(View.VISIBLE);
    }

    public void sendMessage() {
        String message = messageText.getText().toString();
        if (Utilities.isEmpty(message) || Utilities.isEmpty(message.trim())) {
            showSnackBar("Please Enter a message");
        }

        viewModel.saveSentMessage(appPackage,
                Utilities.getAppNameFromPackage(this, appPackage),
                title,
                message,
                tag);

        ReplyIntentSender re = new ReplyIntentSender(replyAction);
        re.sendNativeIntent(this, message);
        messageText.getText().clear();
    }

    @Override
    protected String getBannerId() {
        return Constants.BANNER_TEXT;
    }
}