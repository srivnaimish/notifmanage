package com.notification.core.view.activity.base;

import android.support.annotation.CallSuper;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by anuragdalia on 08/04/18.
 */

public abstract class BaseActivityPresenter<M extends BaseActivityContract.View> implements BaseActivityContract.Presenter {
    public M view;

    public BaseActivityPresenter(M view) {
        this.view = view;
    }

    @Override
    public void init() {

    }

    @Override
    @CallSuper
    public void kill() {

    }

    public boolean isSubscribed() {
        return view.activityIsResumed();
    }

}
