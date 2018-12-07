package messenger.notificationsaver.notification.messenger.messengernotification.view.fragment.base;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by anuragdalia on 27/03/18.
 */

public abstract class BaseFragmentPresenter<M extends BaseFragmentContract.View> implements BaseFragmentContract.Presenter {
    @Inject
    public M view;

    protected CompositeDisposable disposable = new CompositeDisposable();

    public BaseFragmentPresenter(M view) {
        this.view = view;
    }

    @Override
    public void init() {

    }

    @Override
    public void kill() {
        disposable.clear();
    }

    public boolean isSubscribed() {
        return view.isResumed();
    }
}
