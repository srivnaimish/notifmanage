package messenger.notificationsaver.notification.messenger.messengernotification.view.activity.settings;

import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import javax.inject.Inject;

import messenger.notificationsaver.notification.messenger.messengernotification.R;
import messenger.notificationsaver.notification.messenger.messengernotification.model.room.dao.NotificationDao;
import messenger.notificationsaver.notification.messenger.messengernotification.view.activity.base.BaseActivity;

public class SettingsActivity extends BaseActivity {

    @Inject
    NotificationDao notificationDao;

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new MainPreferenceFragment()).commit();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    public void deleteAllNotifications() {
        AsyncTask.execute(() -> notificationDao.deleteNotifications());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(
                android.R.anim.fade_in,
                android.R.anim.fade_out);
    }
}
