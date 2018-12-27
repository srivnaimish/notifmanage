package com.notification.core.view.activity.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.firebase.analytics.FirebaseAnalytics;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

import com.notification.core.R;
import com.notification.core.utils.Constants;
import com.notification.core.utils.SharedPrefUtil;
import com.notification.core.view.callbacks.IHasPermission;
import com.notification.core.view.fragment.base.BaseFragment;

/**
 * Created by Anurag on 10/9/2017.
 */

public abstract class BaseActivity extends DaggerAppCompatActivity {

    protected Context context;
    @Inject
    protected SharedPrefUtil sharedPrefUtil;

    @Inject
    public FirebaseAnalytics mFirebaseAnalytics;

    private Snackbar snackbar;
    protected Long lastBackPressed = 0L;

    public int mScreenHeight;
    public int mScreenWidth;

    private SparseArray<IHasPermission> permsListenerList = new SparseArray<>();
    private ProgressDialog progressDialog;

    public SparseArray<IHasPermission> getPermsListenerList() {
        return permsListenerList;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int layoutId = getLayoutId();
        if (layoutId != 0) {
            setContentView(layoutId);
        }

        //Fabric.with(this, new Crashlytics());
        context = this;
    }

    protected abstract int getLayoutId();

    public void showSnackBar(String text) {
        if (snackbar != null) {
            snackbar.dismiss();
        }
        //Utilities.hideKeyboard(this);
        snackbar = Snackbar.make(this.findViewById(android.R.id.content), text, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    public void open(BaseFragment fragment) {
        Toast.makeText(this, "Override open(BaseFragment fragment) in activity.", Toast.LENGTH_SHORT).show();
    }

    public void checkHasPermission(String permission, int permcode, Action has, Action doesntHave) {
        checkHasPermission(permission, permcode, new IHasPermission() {
            @Override
            public void hasPermission() {
                try {
                    has.run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void doesNotHavePermission() {
                try {
                    doesntHave.run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void checkHasPermission(String permission, int permcode, IHasPermission IHasPermission) {
        if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED) {
            IHasPermission.hasPermission();
        } else {
            permsListenerList.put(permcode, IHasPermission);
            ActivityCompat.requestPermissions(this, new String[]{permission}, permcode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        IHasPermission IHasPermission = permsListenerList.get(requestCode);
        if (IHasPermission != null && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                permsListenerList.get(requestCode).hasPermission();
            else
                permsListenerList.get(requestCode).doesNotHavePermission();
            permsListenerList.remove(requestCode);
        }
    }

    public void goFullScreenWithNoBottomBar(boolean bool) {
        Toast.makeText(this, "Override goFullScreenWithNoBottomBar(boolean bool) in activity.", Toast.LENGTH_SHORT).show();
    }

    public void bindClick(int viewId, Consumer<View> onClick) {
        findViewById(viewId).setOnClickListener(view -> {
            try {
                onClick.accept(view);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void goActivityFullScreen(Boolean bool) {
        getWindow().clearFlags(bool ? WindowManager.LayoutParams.FLAG_FULLSCREEN : WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        getWindow().addFlags(bool ? WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN : WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    protected void loadInterstitial() {

        InterstitialAd mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getInterstitialId());
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            mInterstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdLoaded() {
                    mInterstitialAd.show();
                }
            });
        }
    }

    protected void loadBannerAd() {
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId(getBannerId());

        ViewGroup v = findViewById(R.id.bottom_container);
        v.addView(adView);
        adView.loadAd(new AdRequest.Builder().build());
    }

    protected String getInterstitialId() {
        return Constants.INTERSTITIAL_APP_OPEN;
    }

    protected String getBannerId() {
        return Constants.BANNER_TITLE;
    }
}

