package messenger.notificationsaver.notification.messenger.messengernotification.view.activity.landing;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import messenger.notificationsaver.notification.messenger.messengernotification.view.fragment.allNotifications.AllNotificationsFragment;
import messenger.notificationsaver.notification.messenger.messengernotification.view.fragment.newNotifications.NewNotificationsFragment;

/**
 * Created by naimish on 10/12/2018
 */
public class LandingPagerAdapter extends FragmentPagerAdapter {

    String[] title = {"New","All"};

    public LandingPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return NewNotificationsFragment.newInstance();
            default:
                return AllNotificationsFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
