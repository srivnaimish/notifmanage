package messenger.notificationsaver.notification.messenger.messengernotification.view.viewholders;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;

import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdView;

import messenger.notificationsaver.notification.messenger.messengernotification.model.pojo.BaseRow;

/**
 * Created by anuragdalia on 26/08/18.
 */

public class NativeAdViewHolder extends BaseViewHolder<BaseRow> {
    public NativeAdViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void set(BaseRow baseRow) {

    }

    //private Database database;
    /*private RequestOptions requestOptions;
    private Context context;
    private FrameLayout frameLayout;

    public NativeAdViewHolder(View itemView) {
        super(itemView);
        frameLayout = itemView.findViewById(R.id.fl_adplaceholder);
        context = itemView.getContext();

        requestOptions = new RequestOptions().placeholder(R.drawable.blurred_bg)
                .transforms(new CenterCrop(), new RoundedCorners(20));
        this.database = database;
    }

    *//**
     * Populates a {@link UnifiedNativeAdView} object with data from a given
     * {@link UnifiedNativeAd}.
     *
     * @param nativeAd the object containing the ad's assets
     * @param adView   the view to be populated
     *//*
    private void populateUnifiedNativeAdView(UnifiedNativeAd nativeAd, UnifiedNativeAdView adView) {
        VideoController vc = nativeAd.getVideoController();

        MediaView mediaView = adView.findViewById(R.id.ad_media);
        *//*ImageView mainImageView = adView.findViewById(R.id.ad_image);

        if (vc.hasVideoContent()) {
            adView.setMediaView(mediaView);
            mainImageView.setVisibility(View.GONE);
        } else {
            adView.setImageView(mainImageView);
            mediaView.setVisibility(View.GONE);
            List<NativeAd.Image> images = nativeAd.getImages();
            if (images.size() > 0)
                mainImageView.setImageDrawable(images.get(0).getDrawable());
        }*//*

        adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
        adView.setBodyView(adView.findViewById(R.id.ad_body));
        adView.setCallToActionView(adView.findViewById(R.id.ad_call_to_action));
        adView.setIconView(adView.findViewById(R.id.ad_app_icon));
        adView.setPriceView(adView.findViewById(R.id.ad_price));
        adView.setStarRatingView(adView.findViewById(R.id.ad_stars));
        adView.setStoreView(adView.findViewById(R.id.ad_store));
        adView.setAdvertiserView(adView.findViewById(R.id.ad_advertiser));

        // Some assets are guaranteed to be in every UnifiedNativeAd.
        ((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());
        ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
        ((TextView) adView.getCallToActionView()).setText(nativeAd.getCallToAction());

        // These assets aren't guaranteed to be in every UnifiedNativeAd, so it's important to
        // check before trying to display them.
        if (nativeAd.getIcon() == null) {
            adView.getIconView().setVisibility(View.GONE);
        } else {
            try {
                GlideApp.with(adView.getIconView())
                        .load(nativeAd.getIcon().getDrawable())
                        .apply(requestOptions)
                        .into((ImageView) adView.getIconView());
                adView.getIconView().setVisibility(View.VISIBLE);
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

        if (nativeAd.getPrice() == null) {
            adView.getPriceView().setVisibility(View.INVISIBLE);
        } else {
            adView.getPriceView().setVisibility(View.VISIBLE);
            ((TextView) adView.getPriceView()).setText(nativeAd.getPrice());
        }

        if (nativeAd.getStore() == null) {
            adView.getStoreView().setVisibility(View.INVISIBLE);
        } else {
            adView.getStoreView().setVisibility(View.VISIBLE);
            ((TextView) adView.getStoreView()).setText(nativeAd.getStore());
        }

        if (nativeAd.getStarRating() == null) {
            adView.getStarRatingView().setVisibility(View.INVISIBLE);
        } else {
            ((RatingBar) adView.getStarRatingView())
                    .setRating(nativeAd.getStarRating().floatValue());
            adView.getStarRatingView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getAdvertiser() == null) {
            adView.getAdvertiserView().setVisibility(View.INVISIBLE);
        } else {
            ((TextView) adView.getAdvertiserView()).setText(nativeAd.getAdvertiser());
            adView.getAdvertiserView().setVisibility(View.VISIBLE);
        }

        adView.setNativeAd(nativeAd);
    }

    *//**
     * Creates a request for a new native ad based on the boolean parameters and calls the
     * corresponding "populate" method when one is successfully returned.
     *//*
    private void refreshAd() {

        AdLoader.Builder builder = new AdLoader.Builder(context, ADMOB_NATIVE);

        builder.forUnifiedNativeAd(unifiedNativeAd -> {

            UnifiedNativeAdView adView = (UnifiedNativeAdView) LayoutInflater.from(context).inflate(R.layout.content_ad, null);
            populateUnifiedNativeAdView(unifiedNativeAd, adView);
            frameLayout.removeAllViews();
            frameLayout.addView(adView);
        });

        VideoOptions videoOptions = new VideoOptions.Builder().build();

        NativeAdOptions adOptions = new NativeAdOptions.Builder().setVideoOptions(videoOptions).build();

        builder.withNativeAdOptions(adOptions);

        AdLoader adLoader = builder.withAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(int errorCode) {
                Toast.makeText(context, "Failed to load native ad: " + errorCode, Toast.LENGTH_SHORT).show();
            }
        }).build();

        adLoader.loadAd(Utilities.getAdRequest(database));
    }

    @Override
    public void set(BaseAdRow baseAdRow) {
        refreshAd();
    }*/
}
