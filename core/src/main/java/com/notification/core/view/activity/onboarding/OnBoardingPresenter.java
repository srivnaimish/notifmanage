package com.notification.core.view.activity.onboarding;

import javax.inject.Inject;

import com.notification.core.view.activity.base.BaseActivityPresenter;

/**
 * Created by anuragdalia on 08/04/18.
 */

public class OnBoardingPresenter extends BaseActivityPresenter<OnBoardingContract.View> implements OnBoardingContract.Presenter {

    @Inject
    public OnBoardingPresenter(OnBoardingContract.View view) {
        super(view);
    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public void subscribe() {

    }
}
