package com.notification.core.view.activity.splash;

import javax.inject.Inject;

import com.notification.core.utils.SharedPrefUtil;
import com.notification.core.utils.Utilities;
import com.notification.core.view.activity.base.BaseActivityPresenter;

/**
 * Created by anuragdalia on 08/04/18.
 */

public class SplashPresenter extends BaseActivityPresenter<SplashContract.View> implements SplashContract.Presenter {

    @Inject
    public SplashPresenter(SplashContract.View view) {
        super(view);
    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public void subscribe() {

    }
}
