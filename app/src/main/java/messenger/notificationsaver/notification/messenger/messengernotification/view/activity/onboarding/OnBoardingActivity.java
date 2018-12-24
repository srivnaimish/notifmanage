package messenger.notificationsaver.notification.messenger.messengernotification.view.activity.onboarding;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import messenger.notificationsaver.notification.messenger.messengernotification.R;
import messenger.notificationsaver.notification.messenger.messengernotification.utils.IntentFactory;
import messenger.notificationsaver.notification.messenger.messengernotification.view.activity.base.BaseActivityView;


public class OnBoardingActivity extends BaseActivityView<OnBoardingContract.Presenter> implements OnBoardingContract.View, ViewPager.OnPageChangeListener {

    private int[] pageIndicators = {R.id.b1, R.id.b2};
    private ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewPager = findViewById(R.id.viewpager);
        findViewById(pageIndicators[0]).setSelected(true);
        viewPager.setAdapter(new OnBoardingAdapter());

        viewPager.addOnPageChangeListener(this);
        bindClick(R.id.skip, v -> moveToLandingActivity());
        bindClick(R.id.next, v -> onNext());
    }

    private void onNext() {
        if (viewPager.getCurrentItem() == pageIndicators.length - 1) {
            moveToLandingActivity();
            return;
        }
        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
    }

    private void moveToLandingActivity() {
        sharedPrefUtil.setSeenOnBoarding(true);
        startActivity(IntentFactory.getLandingActivity(OnBoardingActivity.this));
        finish();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_onboarding;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < pageIndicators.length; i++) {
            findViewById(pageIndicators[i]).setSelected(false);
        }
        findViewById(pageIndicators[position]).setSelected(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}