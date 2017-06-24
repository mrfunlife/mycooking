package mycooking.funlife.com.vn.mycooking.ui;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.util.Log;

import icepick.Icepick;
import nucleus.presenter.RxPresenter;

/**
 * Created by hunghd on 4/18/17.
 */

public class BasePresenter<View> extends RxPresenter<View> {

    @CallSuper
    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        Log.d(getClass().getSimpleName(), "load States");
        Icepick.restoreInstanceState(this, savedState);
    }

    @CallSuper
    @Override
    protected void onSave(Bundle state) {
        super.onSave(state);
        Log.d(getClass().getSimpleName(), "save States");
        Icepick.saveInstanceState(this, state);
    }

}
