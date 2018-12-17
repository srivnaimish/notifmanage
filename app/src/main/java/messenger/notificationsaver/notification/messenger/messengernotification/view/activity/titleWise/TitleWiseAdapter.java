package messenger.notificationsaver.notification.messenger.messengernotification.view.activity.titleWise;

import android.content.Context;
import android.support.annotation.NonNull;
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
import messenger.notificationsaver.notification.messenger.messengernotification.view.viewholders.BaseViewHolder;
import messenger.notificationsaver.notification.messenger.messengernotification.view.viewholders.NativeAdViewHolder;
import messenger.notificationsaver.notification.messenger.messengernotification.view.viewholders.TitleNotificationsViewHolder;

/**
 * Created by naimish on 10/12/2018
 */
public class TitleWiseAdapter extends BasePageAdapter<NotificationRow, BaseViewHolder<BaseRow>> {

    Context context;

    @Inject
    public TitleWiseAdapter(@ApplicationContext Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
    }

    public static DiffUtil.ItemCallback<NotificationRow> DIFF_CALLBACK = new DiffUtil.ItemCallback<NotificationRow>() {
        @Override
        public boolean areItemsTheSame(@NonNull NotificationRow oldItem, @NonNull NotificationRow newItem) {
            return oldItem.getTitle().equals(newItem.getTitle());
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
        return new TitleNotificationsViewHolder(inflater.inflate(R.layout.holder_app_title_wise_notification, parent, false), context);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder<BaseRow> holder, int position) {
        if (holder instanceof TitleNotificationsViewHolder) {
            holder.set(getItem(getRealPosition(position)));
        } else {
            holder.set(null);
        }
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

}
