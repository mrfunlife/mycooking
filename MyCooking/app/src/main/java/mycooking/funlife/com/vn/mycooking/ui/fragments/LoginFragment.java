package mycooking.funlife.com.vn.mycooking.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import mycooking.funlife.com.vn.mycooking.R;
import mycooking.funlife.com.vn.mycooking.ui.BaseFragment;

/**
 * Created by funlife on 8/6/17.
 */

public class LoginFragment extends BaseFragment {
    @BindView(R.id.loginViewPager)
    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    @BindView(R.id.loginLayoutDots)
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private int[] layouts;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addControls();
    }

    private void addControls() {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.login_fragment;
    }


    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(layouts[position], container, false);
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }
}
