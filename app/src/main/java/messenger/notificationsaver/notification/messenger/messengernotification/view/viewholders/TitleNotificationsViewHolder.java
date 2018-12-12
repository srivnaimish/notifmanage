package messenger.notificationsaver.notification.messenger.messengernotification.view.viewholders;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import messenger.notificationsaver.notification.messenger.messengernotification.R;
import messenger.notificationsaver.notification.messenger.messengernotification.model.pojo.BaseRow;
import messenger.notificationsaver.notification.messenger.messengernotification.model.pojo.NotificationRow;
import messenger.notificationsaver.notification.messenger.messengernotification.utils.DateTimeUtils;
import messenger.notificationsaver.notification.messenger.messengernotification.utils.Utilities;

/**
 * Created by naimish on 11/12/2018
 */
public class TitleNotificationsViewHolder extends BaseViewHolder<BaseRow> {

    private ImageView notification_icon;
    private TextView title, text, unreadCount;

    public TitleNotificationsViewHolder(@NonNull View itemView) {
        super(itemView);
        notification_icon = itemView.findViewById(R.id.app_icon);
        title = itemView.findViewById(R.id.title);
        text = itemView.findViewById(R.id.text);
        unreadCount = itemView.findViewById(R.id.unread);
    }

    @Override
    public void set(BaseRow baseRow) {
        NotificationRow allNotificationRow = (NotificationRow) baseRow;

        title.setText(allNotificationRow.getTitle());
        text.setText(allNotificationRow.getText());

        if (allNotificationRow.getUnread() == 0) {
            unreadCount.setVisibility(View.GONE);
        } else {
            unreadCount.setText(String.valueOf(allNotificationRow.getUnread()));
            unreadCount.setVisibility(View.VISIBLE);
        }
    }
}
