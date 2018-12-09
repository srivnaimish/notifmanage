package messenger.notificationsaver.notification.messenger.messengernotification.view.fragment.allNotifications;

import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import messenger.notificationsaver.notification.messenger.messengernotification.model.room.dao.NotificationDao;

/**
 * Created by naimish on 10/12/2018
 */
public class AllNotificationsViewModel extends ViewModel {

    NotificationDao notificationDao;

    @Inject
    AllNotificationsViewModel(NotificationDao dao){
        this.notificationDao = dao;
    }
}
