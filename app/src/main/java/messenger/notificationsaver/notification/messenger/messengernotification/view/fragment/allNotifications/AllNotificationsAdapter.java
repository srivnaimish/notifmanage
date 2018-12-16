package messenger.notificationsaver.notification.messenger.messengernotification.view.fragment.allNotifications;

import android.arch.paging.PagedList;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import javax.inject.Inject;

import messenger.notificationsaver.notification.messenger.messengernotification.R;
import messenger.notificationsaver.notification.messenger.messengernotification.model.dagger.qualifiers.ApplicationContext;
import messenger.notificationsaver.notification.messenger.messengernotification.model.pojo.NotificationRow;
import messenger.notificationsaver.notification.messenger.messengernotification.model.pojo.BaseRow;
import messenger.notificationsaver.notification.messenger.messengernotification.utils.Constants;
import messenger.notificationsaver.notification.messenger.messengernotification.utils.Utilities;
import messenger.notificationsaver.notification.messenger.messengernotification.view.adapters.BasePageAdapter;
import messenger.notificationsaver.notification.messenger.messengernotification.view.viewholders.AllNotificationsViewHolder;
import messenger.notificationsaver.notification.messenger.messengernotification.view.viewholders.BaseViewHolder;
import messenger.notificationsaver.notification.messenger.messengernotification.view.viewholders.NativeAdViewHolder;

/**
 * Created by naimish on 10/12/2018
 */
public class AllNotificationsAdapter extends BasePageAdapter<NotificationRow, BaseViewHolder<BaseRow>> {

    Context context;

    @Inject
    public AllNotificationsAdapter(@ApplicationContext Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
    }

    public static DiffUtil.ItemCallback<NotificationRow> DIFF_CALLBACK = new DiffUtil.ItemCallback<NotificationRow>() {

        @Override
        public boolean areItemsTheSame(@NonNull NotificationRow oldItem, @NonNull NotificationRow newItem) {
            return oldItem.getAppPackage().equals(newItem.getAppPackage());
        }

        @Override
        public boolean areContentsTheSame(@NonNull NotificationRow oldItem, @NonNull NotificationRow newItem) {
            return oldItem.equals(newItem);
        }
    };

    @Override
    public int getItemCount() {
        if (Utilities.isEmpty(getCurrentList())) return 0;
        int listSize = getCurrentList().size();
        return listSize + listSize / Constants.AD_REPEAT_POSITION;
    }

    @Override
    public int getItemViewType(int position) {
        if (position != 0 && position % Constants.AD_REPEAT_POSITION == 0) {
            return Constants.AD_ROW;
        }
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public BaseViewHolder<BaseRow> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == Constants.AD_ROW) {
            return new NativeAdViewHolder(context, inflater.inflate(R.layout.holder_ad, parent, false));
        }
        return new AllNotificationsViewHolder(inflater.inflate(R.layout.holder_appwise_notification, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder<BaseRow> holder, int position) {
        if (holder instanceof AllNotificationsViewHolder) {
            holder.set(getItem(getRealPosition(position)));
        } else {
            holder.set(null);
        }
    }

    @Override
    public void submitList(PagedList<NotificationRow> pagedList) {
        super.submitList(pagedList);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

}
