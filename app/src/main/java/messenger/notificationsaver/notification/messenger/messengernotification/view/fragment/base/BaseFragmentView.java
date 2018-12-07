package messenger.notificationsaver.notification.messenger.messengernotification.view.fragment.base;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewAnimationUtils;

import com.google.firebase.analytics.FirebaseAnalytics;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import messenger.notificationsaver.notification.messenger.messengernotification.model.dagger.qualifiers.ApplicationContext;
import messenger.notificationsaver.notification.messenger.messengernotification.view.activity.base.BaseActivityContract;
import messenger.notificationsaver.notification.messenger.messengernotification.view.activity.base.BaseActivityView;
import messenger.notificationsaver.notification.messenger.messengernotification.view.callbacks.IHasPermission;

/**
 * Created by Anurag on 10/30/2017.
 */

public abstract class BaseFragmentView<T extends BaseFragmentContract.Presenter> extends DaggerFragment implements BaseFragmentContract.View {

    @Inject
    @ApplicationContext
    protected Context context;

    @Inject
    public FirebaseAnalytics mFirebaseAnalytics;

    @Inject
    protected T presenter;

    @Override
    public void onResume() {
        super.onResume();
        presenter.subscribe();
    }

    @Override
    public void onPause() {
        presenter.unSubscribe();
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        presenter.kill();
        super.onDestroyView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.init();
    }

    public void open(BaseFragmentView<? extends BaseFragmentContract.Presenter> baseView) {
        if (getParentFragment() != null) {
            ((BaseFragmentView<? extends BaseFragmentContract.Presenter>) getParentFragment()).open(baseView);
        }
    }

    @Override
    public void finishAndOpen(BaseFragmentView<? extends BaseFragmentContract.Presenter> baseView) {
        if (getParentFragment() != null) {
            ((BaseFragmentView<? extends BaseFragmentContract.Presenter>) getParentFragment()).finishAndOpen(baseView);
        }
    }

    @Override
    public void finish() {
        if (getParentFragment() != null) {
            ((BaseFragmentView<? extends BaseFragmentContract.Presenter>) getParentFragment()).finish();
        }
    }

    @Override
    public String getStringFromResources(int stringResource) {
        return context.getString(stringResource);
    }

    public void showSnackBar(String string) {
        runOnUiThread(() -> {
            if (getBaseActivity() != null)
                getBaseActivity().showSnackBar(string);
        });
    }

    public boolean onBackPressed() {
        return true;
    }

    public void checkHasPermission(String permission, int permcode, IHasPermission permissionCallback) {
        BaseActivityView<? extends BaseActivityContract.Presenter> activity = getBaseActivity();
        if (activity != null) {
            if (ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED) {
                permissionCallback.hasPermission();
            } else {
                activity.getPermsListenerList().put(permcode, permissionCallback);
                ActivityCompat.requestPermissions(activity, new String[]{permission}, permcode);
            }
        }

    }

    public void scrollToTop() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!(context instanceof BaseActivityView)) {
            throw new RuntimeException("Only an instance of BaseActivityView can parent a an instance of BaseFragmentView");
        }
    }

    @Nullable
    public BaseActivityView<? extends BaseActivityContract.Presenter> getBaseActivity() {
        return (BaseActivityView<? extends BaseActivityContract.Presenter>) getActivity();
    }

    public void bindClick(View rootView, int viewId, Consumer<View> onClick) {
        bindClick(rootView.findViewById(viewId), onClick);
    }

    public void bindClick(View view, Consumer<View> onClick) {
        if (view == null || onClick == null) return;
        view.setOnClickListener(clickedView -> {
            try {
                onClick.accept(clickedView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void openInviteFriendsActivity() {
        mFirebaseAnalytics.logEvent("invite_screen", new Bundle());
//        startActivity(new Intent(getActivity(), InviteActivity.class));
    }

    protected void runOnUiThread(Action action) {
        if (action == null) return;

        if (Looper.myLooper() == Looper.getMainLooper()) {
            try {
                action.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            BaseActivityView activity = getBaseActivity();
            if (activity != null) activity.runOnUiThread(() -> {
                try {
                    action.run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            else new Handler(Looper.getMainLooper()).post(() -> {
                try {
                    action.run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    @Override
    public void showLoader(boolean show) {
        if (getBaseActivity() != null && isVisible()) {
            getBaseActivity().showLoader(show);
        }
    }

    public interface IDisablePager {
    }

    public interface IShowOnLockScreen {
    }

    public interface IOpenFullscreenWithNoBottom {
    }

    public interface IActivityDisableFullScreen {
    }

    public void doExitReveal(View view) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            int centerX = view.getWidth() / 2;
            int centerY = view.getHeight();
            int startRadius = view.getWidth();
            Animator anim =
                    ViewAnimationUtils.createCircularReveal(view, centerX, centerY, startRadius, 0);
            anim.setDuration(250);
            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    view.setVisibility(View.GONE);
                }
            });
            anim.start();
        } else {
            view.setVisibility(View.GONE);
        }
    }
}
