package mycooking.funlife.com.vn.mycooking.ui;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import butterknife.ButterKnife;
import icepick.Icepick;
import mycooking.funlife.com.vn.mycooking.R;
import nucleus.view.NucleusFragment;

/**
 * Created by hunghd on 4/18/17.
 */

public abstract class BaseFragment<P extends BasePresenter> extends NucleusFragment<P> {

    View mContentView;
    ProgressBar mProgressBar;

    @CallSuper
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Icepick.restoreInstanceState(this, bundle);
    }

    @CallSuper
    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        Icepick.saveInstanceState(this, bundle);
    }

    @CallSuper
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        FrameLayout rootView = (FrameLayout) inflater.inflate(R.layout.fragment_base, container, false);
        mProgressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        ViewStub placeHolderView = (ViewStub) rootView.findViewById(R.id.placeHolderView);
        placeHolderView.setLayoutResource(getLayoutResId());
        mContentView = placeHolderView.inflate();

        ButterKnife.bind(this, rootView);

        return rootView;
    }

    protected abstract int getLayoutResId();

    public void showLoading(boolean flag) {
        if (flag) {
//            mContentView.setVisibility(View.GONE);
            mProgressBar.setVisibility(View.VISIBLE);
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        } else {
//            mContentView.setVisibility(View.VISIBLE);
          getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            mProgressBar.setVisibility(View.GONE);
        }
    }



}
