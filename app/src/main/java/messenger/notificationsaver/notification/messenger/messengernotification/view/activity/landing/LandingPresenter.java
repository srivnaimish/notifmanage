package messenger.notificationsaver.notification.messenger.messengernotification.view.activity.landing;

import android.content.Context;
import android.support.v4.app.NotificationManagerCompat;

import java.util.Set;

import javax.inject.Inject;

import messenger.notificationsaver.notification.messenger.messengernotification.model.dagger.qualifiers.ApplicationContext;
import messenger.notificationsaver.notification.messenger.messengernotification.view.activity.base.BaseActivityPresenter;

/**
 * Created by naimish on 07/12/2018
 */
public class LandingPresenter extends BaseActivityPresenter<LandingContract.View> implements LandingContract.Presenter {

    @Inject
    public LandingPresenter(LandingContract.View view) {
        super(view);
    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public void subscribe() {

    }

    @Override
    public void checkNotificationAccess(Context context) {
        Set<String> enabledPackages = NotificationManagerCompat.getEnabledListenerPackages(context);
        if (!enabledPackages.contains(context.getPackageName())) {
            view.requestNotificationAccess();
        }
    }
}
