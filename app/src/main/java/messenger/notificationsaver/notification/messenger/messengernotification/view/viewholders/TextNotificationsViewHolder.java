package messenger.notificationsaver.notification.messenger.messengernotification.view.viewholders;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
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
        time.setText(DateTimeUtils.getTimeStampAsDate(notificationRow.getTime()));
    }
}
