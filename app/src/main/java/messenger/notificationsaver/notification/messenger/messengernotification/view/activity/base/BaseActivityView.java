package messenger.notificationsaver.notification.messenger.messengernotification.view.activity.base;

import android.app.KeyguardManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import messenger.notificationsaver.notification.messenger.messengernotification.R;
import messenger.notificationsaver.notification.messenger.messengernotification.utils.SharedPrefUtil;
import messenger.notificationsaver.notification.messenger.messengernotification.view.callbacks.IHasPermission;
import messenger.notificationsaver.notification.messenger.messengernotification.view.fragment.base.BaseFragmentView;

import static android.content.Intent.ACTION_USER_PRESENT;

/**
 * Created by Anurag on 10/9/2017.
 */

public abstract class BaseActivityView<T extends BaseActivityContract.Presenter> extends DaggerAppCompatActivity implements BaseActivityContract.View {

    protected Context context;

    @Inject
    protected SharedPrefUtil sharedPrefUtil;

    @Inject
    protected T presenter;

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
    protected void onResume() {
        super.onResume();
        presenter.subscribe();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.unSubscribe();
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
        presenter.init();
    }

    protected abstract int getLayoutId();

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - lastBackPressed < 3000)
            super.onBackPressed();
        else {
            Toast.makeText(this, "Press Back Again to Exit", Toast.LENGTH_LONG).show();
            lastBackPressed = System.currentTimeMillis();
        }
    }

    @Override
    public void showSnackBar(String text) {
        if (snackbar != null) {
            snackbar.dismiss();
        }
        //Utilities.hideKeyboard(this);
        snackbar = Snackbar.make(this.findViewById(android.R.id.content), text, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    public void open(BaseFragmentView fragment) {
        Toast.makeText(this, "Override open(BaseFragmentView fragment) in activity.", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onDestroy() {
        presenter.kill();
        super.onDestroy();
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

    @Override
    public void showLoader(boolean b) {
        if (isFinishing()) return;
        if (b) {
            if (progressDialog != null)
                return;
            this.progressDialog = ProgressDialog.show(this, "", "", true, false);
            if (this.progressDialog.getWindow() == null) return;
            this.progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            // this.progressDialog.setContentView(R.layout.progressdialog);
        } else if (this.progressDialog != null) {
            this.progressDialog.dismiss();
            this.progressDialog = null;
        }
    }

    @Override
    public boolean activityIsResumed() {
        return !(isDestroyed() || isFinishing());
    }

    public void goActivityFullScreen(Boolean bool) {
        getWindow().clearFlags(bool ? WindowManager.LayoutParams.FLAG_FULLSCREEN : WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        getWindow().addFlags(bool ? WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN : WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}

