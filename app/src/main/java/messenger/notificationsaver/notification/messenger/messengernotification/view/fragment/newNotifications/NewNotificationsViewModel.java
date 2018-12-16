package messenger.notificationsaver.notification.messenger.messengernotification.view.fragment.newNotifications;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import javax.inject.Inject;

import messenger.notificationsaver.notification.messenger.messengernotification.model.pojo.NotificationRow;
import messenger.notificationsaver.notification.messenger.messengernotification.model.room.dao.NotificationDao;
import messenger.notificationsaver.notification.messenger.messengernotification.utils.SharedPrefUtil;

/**
 * Created by naimish on 10/12/2018
 */
public class NewNotificationsViewModel extends ViewModel {

    NotificationDao notificationDao;
    SharedPrefUtil sharedPrefUtil;

    @Inject
    NewNotificationsViewModel(NotificationDao dao, SharedPrefUtil sharedPrefUtil) {
        this.notificationDao = dao;
        this.sharedPrefUtil = sharedPrefUtil;
    }

    private PagedList.Config pagedListConfig =
            new PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setPageSize(20)
                    .build();

    public LiveData<PagedList<NotificationRow>> getNewNotificationApps(long time) {
        if (notificationDao == null) {
            return null;
        }


        DataSource.Factory<Integer, NotificationRow> dataSourceFactory
                = notificationDao.getNotificationsSinceLastOpen(time);
        return new LivePagedListBuilder<>(dataSourceFactory, pagedListConfig)
                .build();
    }
}
