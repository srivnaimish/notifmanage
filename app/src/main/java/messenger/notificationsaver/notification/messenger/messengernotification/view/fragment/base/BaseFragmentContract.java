package messenger.notificationsaver.notification.messenger.messengernotification.view.fragment.base;

/**
 * Created by anuragdalia on 27/03/18.
 */

public interface BaseFragmentContract {

    interface View {

        String getTag();

        void showSnackBar(String str);

        void open(BaseFragmentView<? extends Presenter> fragment);

        void finishAndOpen(BaseFragmentView<? extends Presenter> fragment);

        boolean onBackPressed();

        void scrollToTop();

        void openPlayStoreToRate();

        void showLoader(boolean show);

        String getString(int id);

        boolean isResumed();

        void finish();

        String getStringFromResources(int label__name_unblocked);
    }

    interface Presenter {

        void init();

        void unSubscribe();

        void subscribe();

        void kill();

    }
}