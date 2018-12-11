package messenger.notificationsaver.notification.messenger.messengernotification.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import javax.inject.Inject;

import messenger.notificationsaver.notification.messenger.messengernotification.application.NotificationApplication;
import messenger.notificationsaver.notification.messenger.messengernotification.utils.SharedPrefUtil;
import messenger.notificationsaver.notification.messenger.messengernotification.utils.Utilities;

/**
 * Created by naimish on 09/12/2018
 */
public class AppInstallUninstallReceiver extends BroadcastReceiver {

   /* @Inject
    SharedPrefUtil sharedPrefUtil;*/

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action == null) {
            return;
        }
        //sharedPrefUtil.saveInstalledApps(Utilities.getInstalledAppsList(context));
    }
}
