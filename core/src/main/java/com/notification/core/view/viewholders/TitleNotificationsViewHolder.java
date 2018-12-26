package com.notification.core.view.viewholders;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.notification.core.R;
import com.notification.core.model.pojo.BaseRow;
import com.notification.core.model.pojo.NotificationRow;
import com.notification.core.utils.DateTimeUtils;
import com.notification.core.utils.Utilities;

/**
 * Created by naimish on 11/12/2018
 */
public class TitleNotificationsViewHolder extends BaseViewHolder<BaseRow> {

    private ImageView notification_icon, person_icon;
    private TextView title, text, time, unreadCount;

    private Context context;

    public TitleNotificationsViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        person_icon = itemView.findViewById(R.id.person_icon);
        notification_icon = itemView.findViewById(R.id.notification_icon);
        title = itemView.findViewById(R.id.title);
        text = itemView.findViewById(R.id.text);
        time = itemView.findViewById(R.id.time);
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
            person_icon.setVisibility(View.GONE);
        } else {
            notification_icon.setImageResource(R.color.grey);
            person_icon.setVisibility(View.VISIBLE);
        }

        time.setText(DateTimeUtils.getMaterialUpdatedTimeString(context, notificationRow.getTime()));

    }
}
