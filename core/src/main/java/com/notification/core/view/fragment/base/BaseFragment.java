package com.notification.core.view.fragment.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import io.reactivex.functions.Consumer;

import com.notification.core.utils.SharedPrefUtil;
import com.notification.core.view.activity.base.BaseActivityView;

/**
 * Created by Anurag on 10/30/2017.
 */

public abstract class BaseFragment extends DaggerFragment {

    private static final String TAG = "BaseFragment";

    private boolean isDestroyed = false;

    protected abstract int getLayoutId();

    protected abstract void onBindView(View rootView, Bundle savedInstanceState);

    private View mRootView;

    @Inject
    protected SharedPrefUtil database;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isDestroyed = false;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayoutId(), container, false);
        onBindView(mRootView, savedInstanceState);
        return mRootView;
    }

    @Nullable
    @Override
    public final View getView() {
        return mRootView;
    }

    protected void setRootView(View rootView) {
        this.mRootView = rootView;
    }

    public View findViewById(int viewId) {
        return getView() != null ? getView().findViewById(viewId) : null;
    }

    protected void showError(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    protected void setText(int id, String text) {
        if (getView() == null) {
            return;
        }

        TextView textView = (TextView) getView().findViewById(id);
        if (text != null && textView != null) {
            textView.setText(text);
        }
    }

    public boolean onBackPressed() {
        return false;
    }

    public boolean isDestroyed() {
        return isDestroyed || getActivity() == null;
    }

    @Override
    public void onDestroy() {
        isDestroyed = true;
        super.onDestroy();
    }

    public View getRootView() {
        return mRootView;
    }

    public void bindClick(int viewId, Consumer<View> onClick) {
        getRootView().findViewById(viewId).setOnClickListener(view -> {
            try {
                onClick.accept(view);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void onInviteClick() {
        String message = "Share messages that hatch in the future with me with Hakomuna. Get the app now ";
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_TEXT, message);
        startActivity(Intent.createChooser(share, "Invite via.."));
    }
}
