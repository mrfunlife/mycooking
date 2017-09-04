package mycooking.funlife.com.vn.mycooking.ui.activitys;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import mycooking.funlife.com.vn.mycooking.R;
import mycooking.funlife.com.vn.mycooking.adapter.ViewPagerAdapter;
import mycooking.funlife.com.vn.mycooking.customview.LockedViewpager;
import mycooking.funlife.com.vn.mycooking.customview.flowerdrawer.ElasticDrawer;
import mycooking.funlife.com.vn.mycooking.customview.flowerdrawer.FlowingDrawer;
import mycooking.funlife.com.vn.mycooking.interfaces.OpenDrawerLayout;
import mycooking.funlife.com.vn.mycooking.ui.fragments.AboutFragment;
import mycooking.funlife.com.vn.mycooking.ui.fragments.HistoryFragment;
import mycooking.funlife.com.vn.mycooking.ui.fragments.HomeFragment;
import mycooking.funlife.com.vn.mycooking.ui.fragments.UtilsFragment;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class HomeActivity extends AppCompatActivity implements OpenDrawerLayout{
    LockedViewpager viewPager;
//    TabLayout tabLayout;
    BottomBar bottomBar;
    private FlowingDrawer mDrawer;


    private HomeFragment homeFragment = new HomeFragment();
    private HistoryFragment historyFragment = new HistoryFragment();
    private UtilsFragment utilsFragment = new UtilsFragment();
    private AboutFragment aboutFragment = new AboutFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_bar_layout);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(Color.TRANSPARENT);
                getWindow()
                        .getDecorView()
                        .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            } else {
                getWindow()
                        .setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
        }
        addControls();

    }

    private void addControls() {
        mDrawer = (FlowingDrawer) findViewById(R.id.drawerlayout);
        mDrawer.setTouchMode(ElasticDrawer.TOUCH_MODE_BEZEL);
        viewPager = (LockedViewpager) findViewById(R.id.homeViewPager);
        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        setupViewPager(homeFragment,historyFragment,utilsFragment,aboutFragment);
        bottomBar.setDefaultTab(R.id.tab_food);
        viewPager.setCurrentItem(1);

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId){
                    case R.id.tab_recents:
                        viewPager.setCurrentItem(0,false);
                        break;
                    case R.id.tab_favorites:
                        viewPager.setCurrentItem(2,false);
                        break;
                    case R.id.tab_food:
                        viewPager.setCurrentItem(1,false);
                        break;
                }
            }
        });
        viewPager.setSwipeable(false);

    }

    public void onBackPressed() {
        if (mDrawer.isMenuVisible()) {
            mDrawer.closeMenu();
        } else {
            super.onBackPressed();
        }
    }

    private void setupViewPager(HomeFragment homeFragment,HistoryFragment historyFragment,UtilsFragment utilsFragment,AboutFragment aboutFragment) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        adapter.addFrag(historyFragment, "HISTORY");
        adapter.addFrag(homeFragment, "MENU");
        adapter.addFrag(utilsFragment, "UTILS");
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(base));
    }

    @Override
    public void openDrawerLayout() {
        if(mDrawer.isMenuVisible()){
            mDrawer.closeMenu(true);
        }else {
            mDrawer.openMenu(true);
        }
    }
}
