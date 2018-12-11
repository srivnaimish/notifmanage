package messenger.notificationsaver.notification.messenger.messengernotification.view.viewholders;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import messenger.notificationsaver.notification.messenger.messengernotification.R;
import messenger.notificationsaver.notification.messenger.messengernotification.model.pojo.AllNotificationRow;
import messenger.notificationsaver.notification.messenger.messengernotification.model.pojo.BaseRow;
import messenger.notificationsaver.notification.messenger.messengernotification.utils.DateTimeUtils;
import messenger.notificationsaver.notification.messenger.messengernotification.utils.Utilities;

/**
 * Created by naimish on 11/12/2018
 */
public class AllNotificationsViewHolder extends BaseViewHolder<BaseRow> {

    private ImageView appIcon;
    private TextView appName, title, text, unreadCount, time;

    public AllNotificationsViewHolder(@NonNull View itemView) {
        super(itemView);
        appIcon = itemView.findViewById(R.id.app_icon);
        appName = itemView.findViewById(R.id.app_name);
        title = itemView.findViewById(R.id.title);
        text = itemView.findViewById(R.id.text);
        appIcon = itemView.findViewById(R.id.app_icon);
        unreadCount = itemView.findViewById(R.id.unread_count);
        time = itemView.findViewById(R.id.time);
    }

    @Override
    public void set(BaseRow baseRow) {
        AllNotificationRow allNotificationRow = (AllNotificationRow) baseRow;

        appIcon.setImageDrawable(Utilities.getAppIconFromPackage(appIcon.getContext(), allNotificationRow.getAppPackage()));
        appName.setText(Utilities.getAppNameFromPackage(appName.getContext(), allNotificationRow.getAppPackage()));
        title.setText(allNotificationRow.getTitle());
        text.setText(allNotificationRow.getText());
        time.setText(DateTimeUtils.getMaterialUpdatedTimeString(time.getContext(), allNotificationRow.getTime()));

        if (allNotificationRow.getUnread() == 0) {
            unreadCount.setVisibility(View.GONE);
        } else {
            unreadCount.setText(unreadCount.getContext().getString(R.string.unread_count, String.valueOf(allNotificationRow.getUnread())));
            unreadCount.setVisibility(View.VISIBLE);
        }
    }
}
