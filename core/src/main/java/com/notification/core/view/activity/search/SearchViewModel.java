package com.notification.core.view.activity.search;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import javax.inject.Inject;

import com.notification.core.model.pojo.SearchRow;
import com.notification.core.model.room.dao.NotificationDao;

/**
 * Created by naimish on 10/12/2018
 */
public class SearchViewModel extends ViewModel {

    private NotificationDao notificationDao;

    @Inject
    SearchViewModel(NotificationDao dao) {
        this.notificationDao = dao;
    }

    private PagedList.Config pagedListConfig =
            new PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setPageSize(20)
                    .build();

    public LiveData<PagedList<SearchRow>> getSearchResults(String query) {
        if (notificationDao == null) {
            return null;
        }
        DataSource.Factory<Integer, SearchRow> dataSourceFactory = notificationDao.getSearchQuery(query);
        return new LivePagedListBuilder<>(dataSourceFactory, pagedListConfig)
                .build();
    }
}
