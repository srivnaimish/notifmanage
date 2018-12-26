package com.notification.core.view.activity.base;

import com.notification.core.view.fragment.base.BaseFragment;

/**
 * Created by anuragdalia on 27/03/18.
 */

public interface BaseActivityContract {

    interface View {

        void showSnackBar(String str);

        void open(BaseFragment fragment);

        void showLoader(boolean b);

        String getString(int id);

        boolean activityIsResumed();

        void finish();
    }

    interface Presenter {

        void init();

        void unSubscribe();

        void subscribe();

        void kill();
    }
}