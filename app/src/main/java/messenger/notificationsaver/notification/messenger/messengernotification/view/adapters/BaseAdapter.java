package messenger.notificationsaver.notification.messenger.messengernotification.view.adapters;

import android.support.v7.widget.RecyclerView;

import messenger.notificationsaver.notification.messenger.messengernotification.utils.Constants;

/**
 * Created by anuragdalia on 25/03/18.
 */

public abstract class BaseAdapter<T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T> {

    public int getRealPosition(int position) {
        return position - position / Constants.AD_REPEAT_POSITION;
    }
}
