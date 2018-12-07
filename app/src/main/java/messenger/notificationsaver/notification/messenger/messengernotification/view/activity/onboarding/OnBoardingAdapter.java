package messenger.notificationsaver.notification.messenger.messengernotification.view.activity.onboarding;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import messenger.notificationsaver.notification.messenger.messengernotification.R;


/**
 * Created by naimish on 12/11/2018
 */
public class OnBoardingAdapter extends PagerAdapter {

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.onboarding_pageritem, null);
        ImageView displayImage = view.findViewById(R.id.image);
        TextView heading = view.findViewById(R.id.heading);
        TextView subheading = view.findViewById(R.id.subheading);
        switch (position) {
            case 0:
               /* displayImage.setImageResource(R.drawable.onboarding_1);
                heading.setText(R.string.onboarding_heading_1);
                subheading.setText(R.string.onboarding_subheading_1);*/
                break;
            case 1:
                /*displayImage.setImageResource(R.drawable.onboarding_2);
                heading.setText(R.string.onboarding_heading_2);
                subheading.setText(R.string.onboarding_subheading_2);*/
                break;
            case 2:
               /* displayImage.setImageResource(R.drawable.onboarding_3);
                heading.setText(R.string.onboarding_heading_3);
                subheading.setText(R.string.onboarding_subheading_3);*/
                break;
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
