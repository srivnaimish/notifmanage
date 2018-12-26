package com.notification.core.view.viewholders;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import javax.inject.Inject;

import com.notification.core.R;
import com.notification.core.model.dagger.qualifiers.ApplicationContext;
import com.notification.core.model.pojo.BaseRow;
import com.notification.core.model.pojo.NotificationRow;
import com.notification.core.utils.DateTimeUtils;

/**
 * Created by naimish on 11/12/2018
 */
public class TextNotificationsViewHolder extends BaseViewHolder<BaseRow> {

    private TextView text, time;
    private ImageView image;
    private View image_card;

    @Inject
    @ApplicationContext
    Context context;

    public TextNotificationsViewHolder(@NonNull View itemView) {
        super(itemView);
        text = itemView.findViewById(R.id.text);
        image = itemView.findViewById(R.id.image);
        time = itemView.findViewById(R.id.time);
        image_card = itemView.findViewById(R.id.image_card);
    }

    @Override
    public void set(BaseRow baseRow) {
        NotificationRow notificationRow = (NotificationRow) baseRow;

        text.setText(notificationRow.getText());
        time.setText(DateTimeUtils.getTimeStampAsTime(notificationRow.getTime()));
    }
}
