package messenger.notificationsaver.notification.messenger.messengernotification.view.adapters;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

import messenger.notificationsaver.notification.messenger.messengernotification.utils.Constants;
import messenger.notificationsaver.notification.messenger.messengernotification.view.viewholders.BaseViewHolder;

public abstract class BasePageAdapter<T,W extends BaseViewHolder> extends PagedListAdapter<T, W> {
    protected BasePageAdapter(@NonNull DiffUtil.ItemCallback<T> diffCallback) {
        super(diffCallback);
    }

    public int getRealPosition(int position) {
        return position - position / Constants.AD_REPEAT_POSITION;
    }
}