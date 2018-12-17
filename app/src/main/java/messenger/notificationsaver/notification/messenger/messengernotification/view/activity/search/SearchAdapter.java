package messenger.notificationsaver.notification.messenger.messengernotification.view.activity.search;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import javax.inject.Inject;

import messenger.notificationsaver.notification.messenger.messengernotification.R;
import messenger.notificationsaver.notification.messenger.messengernotification.model.dagger.qualifiers.ApplicationContext;
import messenger.notificationsaver.notification.messenger.messengernotification.model.pojo.BaseRow;
import messenger.notificationsaver.notification.messenger.messengernotification.model.pojo.SearchRow;
import messenger.notificationsaver.notification.messenger.messengernotification.view.adapters.BasePageAdapter;
import messenger.notificationsaver.notification.messenger.messengernotification.view.viewholders.BaseViewHolder;
import messenger.notificationsaver.notification.messenger.messengernotification.view.viewholders.SearchViewHolder;

/**
 * Created by naimish on 10/12/2018
 */
public class SearchAdapter extends BasePageAdapter<SearchRow, BaseViewHolder<BaseRow>> {

    Context context;

    @Inject
    public SearchAdapter(@ApplicationContext Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
    }

    public static DiffUtil.ItemCallback<SearchRow> DIFF_CALLBACK = new DiffUtil.ItemCallback<SearchRow>() {
        @Override
        public boolean areItemsTheSame(@NonNull SearchRow oldItem, @NonNull SearchRow newItem) {
            return oldItem.getText().equals(newItem.getText());
        }

        @Override
        public boolean areContentsTheSame(@NonNull SearchRow oldItem, @NonNull SearchRow newItem) {
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
        return new SearchViewHolder(context, inflater.inflate(R.layout.holder_search_notification, parent, false));
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
