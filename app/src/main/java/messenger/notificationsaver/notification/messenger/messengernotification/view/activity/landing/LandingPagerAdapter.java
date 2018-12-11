package messenger.notificationsaver.notification.messenger.messengernotification.view.activity.landing;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import messenger.notificationsaver.notification.messenger.messengernotification.view.fragment.allNotifications.AllNotificationsFragment;

/**
 * Created by naimish on 10/12/2018
 */
public class LandingPagerAdapter extends FragmentPagerAdapter {
    public LandingPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return AllNotificationsFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 1;
    }
}
