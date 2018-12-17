package messenger.notificationsaver.notification.messenger.messengernotification.view.activity.textWise;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import messenger.notificationsaver.notification.messenger.messengernotification.R;
import messenger.notificationsaver.notification.messenger.messengernotification.model.pojo.BaseRow;
import messenger.notificationsaver.notification.messenger.messengernotification.model.pojo.NotificationRow;
import messenger.notificationsaver.notification.messenger.messengernotification.view.adapters.BasePageAdapter;
import messenger.notificationsaver.notification.messenger.messengernotification.view.viewholders.BaseViewHolder;
import messenger.notificationsaver.notification.messenger.messengernotification.view.viewholders.TextNotificationsViewHolder;

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
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public BaseViewHolder<BaseRow> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
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
