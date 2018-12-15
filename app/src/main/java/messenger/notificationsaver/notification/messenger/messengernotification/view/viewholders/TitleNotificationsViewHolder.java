package messenger.notificationsaver.notification.messenger.messengernotification.view.viewholders;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import javax.inject.Inject;

import messenger.notificationsaver.notification.messenger.messengernotification.R;
import messenger.notificationsaver.notification.messenger.messengernotification.model.dagger.qualifiers.ApplicationContext;
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

    private Context context;

    public TitleNotificationsViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        notification_icon = itemView.findViewById(R.id.notification_icon);
        title = itemView.findViewById(R.id.title);
        text = itemView.findViewById(R.id.text);
        unreadCount = itemView.findViewById(R.id.unread);
        this.context = context;
    }

    @Override
    public void set(BaseRow baseRow) {
        NotificationRow notificationRow = (NotificationRow) baseRow;

        title.setText(notificationRow.getTitle());
        text.setText(notificationRow.getText());

        if (notificationRow.getUnread() == 0) {
            unreadCount.setVisibility(View.GONE);
        } else {
            unreadCount.setText(String.valueOf(notificationRow.getUnread()));
            unreadCount.setVisibility(View.VISIBLE);
        }

        if (Utilities.isEmpty(notificationRow.getCategory()) || !notificationRow.getCategory().equalsIgnoreCase("android.app.Notification$MessagingStyle")) {
            notification_icon.setImageDrawable(Utilities.getAppIconFromPackage(context, notificationRow.getAppPackage()));
        } else {
            notification_icon.setImageResource(R.color.grey);
        }

    }
}
