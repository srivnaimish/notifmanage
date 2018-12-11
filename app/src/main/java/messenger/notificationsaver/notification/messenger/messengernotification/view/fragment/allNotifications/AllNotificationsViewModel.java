package messenger.notificationsaver.notification.messenger.messengernotification.view.fragment.allNotifications;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import java.util.List;

import javax.inject.Inject;

import messenger.notificationsaver.notification.messenger.messengernotification.model.pojo.AllNotificationRow;
import messenger.notificationsaver.notification.messenger.messengernotification.model.room.dao.NotificationDao;
import messenger.notificationsaver.notification.messenger.messengernotification.model.room.entity.NotificationEntity;

/**
 * Created by naimish on 10/12/2018
 */
public class AllNotificationsViewModel extends ViewModel {

    NotificationDao notificationDao;

    @Inject
    AllNotificationsViewModel(NotificationDao dao) {
        this.notificationDao = dao;
    }

    private PagedList.Config pagedListConfig =
            new PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setPageSize(20)
                    .build();

    public LiveData<PagedList<AllNotificationRow>> getAppsWithNotifications() {
        if (notificationDao == null) {
            return null;
        }
        DataSource.Factory<Integer, AllNotificationRow> dataSourceFactory = notificationDao.getAppsWithNotification();
        return new LivePagedListBuilder<>(dataSourceFactory, pagedListConfig)
                .build();
    }
}
