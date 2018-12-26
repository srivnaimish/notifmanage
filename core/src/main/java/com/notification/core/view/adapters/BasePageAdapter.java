package com.notification.core.view.adapters;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

import com.notification.core.utils.Constants;
import com.notification.core.view.viewholders.BaseViewHolder;

public abstract class BasePageAdapter<T,W extends BaseViewHolder> extends PagedListAdapter<T, W> {
    protected BasePageAdapter(@NonNull DiffUtil.ItemCallback<T> diffCallback) {
        super(diffCallback);
    }

    public int getRealPosition(int position) {
        return position - position / Constants.AD_REPEAT_POSITION;
    }
}