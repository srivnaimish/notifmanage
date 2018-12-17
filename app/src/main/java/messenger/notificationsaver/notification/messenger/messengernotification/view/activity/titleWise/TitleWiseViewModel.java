package messenger.notificationsaver.notification.messenger.messengernotification.view.activity.titleWise;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.os.AsyncTask;

import javax.inject.Inject;

import messenger.notificationsaver.notification.messenger.messengernotification.model.pojo.NotificationRow;
import messenger.notificationsaver.notification.messenger.messengernotification.model.room.dao.NotificationDao;

/**
 * Created by naimish on 10/12/2018
 */
public class TitleWiseViewModel extends ViewModel {

    private NotificationDao notificationDao;

    @Inject
    TitleWiseViewModel(NotificationDao dao) {
        this.notificationDao = dao;
    }

    private PagedList.Config pagedListConfig =
            new PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setPageSize(20)
                    .build();

    public LiveData<PagedList<NotificationRow>> getAppWiseNotifications(String appPackage) {
        if (notificationDao == null) {
            return null;
        }
        DataSource.Factory<Integer, NotificationRow> dataSourceFactory = notificationDao.getTitleWiseNotifications(appPackage);
        return new LivePagedListBuilder<>(dataSourceFactory, pagedListConfig)
                .build();
    }

    public void markAppNotificationsRead(String appPackage, String title) {
        AsyncTask.execute(() -> notificationDao.readNotificationsOfPackage(appPackage, title));
    }
}
