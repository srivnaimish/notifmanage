package messenger.notificationsaver.notification.messenger.messengernotification.view.activity.appWiseNotifications;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import javax.inject.Inject;

import messenger.notificationsaver.notification.messenger.messengernotification.model.pojo.NotificationRow;
import messenger.notificationsaver.notification.messenger.messengernotification.model.room.dao.NotificationDao;

/**
 * Created by naimish on 10/12/2018
 */
public class AppWiseViewModel extends ViewModel {

    NotificationDao notificationDao;

    @Inject
    AppWiseViewModel(NotificationDao dao) {
        this.notificationDao = dao;
    }

    private PagedList.Config pagedListConfig =
            new PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setPageSize(20)
                    .build();

    public LiveData<PagedList<NotificationRow>> getAppWiseNotifications() {
        if (notificationDao == null) {
            return null;
        }
        DataSource.Factory<Integer, NotificationRow> dataSourceFactory = notificationDao.getAppWiseNotifications();
        return new LivePagedListBuilder<>(dataSourceFactory, pagedListConfig)
                .build();
    }
}
