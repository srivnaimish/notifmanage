package com.notification.core.view.activity.textWise;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.notification.core.R;
import com.notification.core.model.pojo.BaseRow;
import com.notification.core.model.pojo.NotificationRow;
import com.notification.core.utils.Constants;
import com.notification.core.view.adapters.BasePageAdapter;
import com.notification.core.view.viewholders.BaseViewHolder;
import com.notification.core.view.viewholders.TextNotificationsViewHolder;

/**
 * Created by naimish on 10/12/2018
 */
public class NotificationTextAdapter extends BasePageAdapter<NotificationRow, BaseViewHolder<BaseRow>> {

    public NotificationTextAdapter() {
        super(DIFF_CALLBACK);
    }

    public static DiffUtil.ItemCallback<NotificationRow> DIFF_CALLBACK = new DiffUtil.ItemCallback<NotificationRow>() {
        @Override
        public boolean areItemsTheSame(@NonNull NotificationRow oldItem, @NonNull NotificationRow newItem) {
            return oldItem.getTime() == newItem.getTime();
        }

        @Override
        public boolean areContentsTheSame(@NonNull NotificationRow oldItem, @NonNull NotificationRow newItem) {
            return oldItem.equals(newItem);
        }
    };

    @Override
    public int getItemViewType(int position) {
        if (getCurrentList() == null) {
            return super.getItemViewType(position);
        }

        if (getCurrentList().get(position).getRecipient() == 0) {
            return Constants.RECEIVED_MESSAGE_TYPE;
        } else {
            return Constants.SENT_MESSAGE_TYPE;
        }
    }

    @NonNull
    @Override
    public BaseViewHolder<BaseRow> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == Constants.SENT_MESSAGE_TYPE) {
            return new TextNotificationsViewHolder(inflater.inflate(R.layout.holder_sent_text, parent, false));
        }
        return new TextNotificationsViewHolder(inflater.inflate(R.layout.holder_received_text, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder<BaseRow> holder, int position) {
        holder.set(getItem(position));
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

}
