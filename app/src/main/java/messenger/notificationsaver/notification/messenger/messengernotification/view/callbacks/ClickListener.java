package messenger.notificationsaver.notification.messenger.messengernotification.view.callbacks;

import android.view.View;

public interface ClickListener {
    void onClick(View view, int position);

    void onLongClick(View view, int position);
}