package messenger.notificationsaver.notification.messenger.messengernotification.view.activity.splash;

import android.content.Context;

import org.json.JSONArray;

import javax.inject.Inject;

import messenger.notificationsaver.notification.messenger.messengernotification.model.dagger.qualifiers.ApplicationContext;
import messenger.notificationsaver.notification.messenger.messengernotification.utils.SharedPrefUtil;
import messenger.notificationsaver.notification.messenger.messengernotification.utils.Utilities;
import messenger.notificationsaver.notification.messenger.messengernotification.view.activity.base.BaseActivityPresenter;

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
