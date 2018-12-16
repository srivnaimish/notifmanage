package messenger.notificationsaver.notification.messenger.messengernotification.view.viewholders;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdView;

import java.util.List;

import messenger.notificationsaver.notification.messenger.messengernotification.R;
import messenger.notificationsaver.notification.messenger.messengernotification.model.pojo.BaseRow;
import messenger.notificationsaver.notification.messenger.messengernotification.utils.Constants;
import messenger.notificationsaver.notification.messenger.messengernotification.utils.Utilities;

/**
 * Created by anuragdalia on 26/08/18.
 */

public class NativeAdViewHolder extends BaseViewHolder<BaseRow> {

    Context context;
    private FrameLayout adPlaceHolder;

    public NativeAdViewHolder(Context context, @NonNull View itemView) {
        super(itemView);
        this.context = context;
        adPlaceHolder = itemView.findViewById(R.id.ad_holder);
    }

    @Override
    public void set(BaseRow baseRow) {
        refreshAd();
    }

    private void populateUnifiedNativeAdView(UnifiedNativeAd nativeAd, UnifiedNativeAdView adView) {
        adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
        adView.setBodyView(adView.findViewById(R.id.ad_body));
        adView.setImageView(adView.findViewById(R.id.ad_image));
        adView.setCallToActionView(adView.findViewById(R.id.ad_call_to_action));
        adView.setIconView(adView.findViewById(R.id.ad_app_icon));
        adView.setAdvertiserView(adView.findViewById(R.id.ad_advertiser));

        // Some assets are guaranteed to be in every UnifiedNativeAd.
        ((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());
        ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
        ((TextView) adView.getCallToActionView()).setText(nativeAd.getCallToAction());

        // These assets aren't guaranteed to be in every UnifiedNativeAd, so it's important to
        // check before trying to display them.
        List<NativeAd.Image> images = nativeAd.getImages();
        if (images == null || images.size() == 0) {
            adView.getImageView().setVisibility(View.GONE);
        } else {
            ((ImageView) adView.getImageView()).setImageDrawable(images.get(0).getDrawable());
        }

        if (nativeAd.getIcon() != null) {
            try {
                Glide.with(adView.getIconView())
                        .load(nativeAd.getIcon().getDrawable())
                        .into((ImageView) adView.getIconView());
                adView.getIconView().setVisibility(View.VISIBLE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (nativeAd.getAdvertiser() == null) {
            adView.getAdvertiserView().setVisibility(View.INVISIBLE);
        } else {
            ((TextView) adView.getAdvertiserView()).setText(nativeAd.getAdvertiser());
            adView.getAdvertiserView().setVisibility(View.VISIBLE);
        }

        adView.setNativeAd(nativeAd);
    }

    private void refreshAd() {

        AdLoader.Builder builder = new AdLoader.Builder(context, Constants.ADMOB_NATIVE);

        builder.forUnifiedNativeAd(unifiedNativeAd -> {

            UnifiedNativeAdView adView = (UnifiedNativeAdView) LayoutInflater.from(context).inflate(R.layout.native_ad, null);
            populateUnifiedNativeAdView(unifiedNativeAd, adView);
            adPlaceHolder.removeAllViews();
            adPlaceHolder.addView(adView);
        });

        NativeAdOptions adOptions = new NativeAdOptions.Builder().build();

        builder.withNativeAdOptions(adOptions);

        AdLoader adLoader = builder.withAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(int errorCode) {

            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                adPlaceHolder.setVisibility(View.VISIBLE);
            }
        }).build();

        adLoader.loadAd(Utilities.getAdRequest());
    }
}
