package mycooking.funlife.com.vn.mycooking.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import mycooking.funlife.com.vn.mycooking.R;
import nucleus.view.NucleusAppCompatActivity;
import rx.Subscription;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by hunghd on 4/18/17.
 */

public abstract class BaseActivity<P extends BasePresenter> extends NucleusAppCompatActivity<P> {

    public final static int ID_FRAGMENT = R.id.fragment_container;

    private Toolbar mToolbar;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private int mItemToOpenWhenDrawerCloses = -1;
    private Subscription subcriptipon;
    private AsyncTask<Void, Void, Void> taskLogout;
    protected abstract Fragment createRootFragment();

    @CallSuper
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_base);


        FragmentManager fm = getFragmentManager();
        Fragment fragment = fm.findFragmentById(ID_FRAGMENT);
        if (fragment == null) {
            fragment = createRootFragment();
            fm.beginTransaction().replace(ID_FRAGMENT, fragment).commit();
        }


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
    }



    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(base));
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (mDrawerToggle != null) {
            mDrawerToggle.syncState();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (mDrawerToggle != null) {
            mDrawerToggle.onConfigurationChanged(newConfig);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle != null && mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // If not handled by drawerToggle, home needs to be handled by returning to previous
        if (item != null && item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // If the drawer is open, back will close it
        if (mDrawerLayout != null && mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawers();
            return;
        }
        // Otherwise, it may return to the previous fragment stack
        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
        } else {
            // Lastly, it will rely on the system behavior for back
            super.onBackPressed();
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        mToolbar.setTitle(title);
    }

    @Override
    public void setTitle(int titleId) {
        super.setTitle(titleId);
        mToolbar.setTitle(titleId);
    }


    private void populateDrawerItems(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(menuItem -> {
            menuItem.setChecked(true);
            mItemToOpenWhenDrawerCloses = menuItem.getItemId();
            mDrawerLayout.closeDrawers();
            return true;
        });
//        if (HomeActivity.class.isAssignableFrom(getClass())) {
//            navigationView.setCheckedItem(R.id.drawer_item_home);
//        } else if (RequestActivity.class.isAssignableFrom(getClass())) {
//            navigationView.setCheckedItem(R.id.drawer_item_order);
//        } else if (ContractsActivity.class.isAssignableFrom(getClass())) {
//            navigationView.setCheckedItem(R.id.drawer_item_contract);
//        } else if (NotificationActivity.class.isAssignableFrom(getClass())) {
//            navigationView.setCheckedItem(R.id.drawer_item_notification);
//        } else if (ProfileActivity.class.isAssignableFrom(getClass())) {
//            navigationView.setCheckedItem(R.id.drawer_item_profile);
//        }
    }

    protected void updateDrawerToggle() {
        if (mDrawerToggle == null) {
            return;
        }
        boolean isRoot = getFragmentManager().getBackStackEntryCount() == 0;
        mDrawerToggle.setDrawerIndicatorEnabled(isRoot);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(!isRoot);
            getSupportActionBar().setDisplayHomeAsUpEnabled(!isRoot);
            getSupportActionBar().setHomeButtonEnabled(!isRoot);
        }
        if (isRoot) {
            mDrawerToggle.syncState();
        }
    }

    private final DrawerLayout.DrawerListener mDrawerListener = new DrawerLayout.DrawerListener() {
        @Override
        public void onDrawerClosed(View drawerView) {
//            if (mDrawerToggle != null) mDrawerToggle.onDrawerClosed(drawerView);
//            if (mItemToOpenWhenDrawerCloses >= 0) {
////                Bundle extras = ActivityOptions.makeCustomAnimation(
////                        BaseActivity.this, R.anim.activity_in, R.anim.activity_out).toBundle();
//
//                Class activityClass = null;
//                switch (mItemToOpenWhenDrawerCloses) {
//                    case R.id.drawer_item_home:
//                        activityClass = HomeActivity.class;
//                        break;
//                    case R.id.drawer_item_order:
//                        activityClass = RequestActivity.class;
//                        break;
//                    case R.id.drawer_item_contract:
//                        activityClass = ContractsActivity.class;
//                        break;
//                    case R.id.drawer_item_notification:
//                        activityClass = NotificationActivity.class;
//                        break;
//                    case R.id.drawer_item_profile:
//                        activityClass = ProfileActivity.class;
//                        break;
//                    case R.id.drawer_item_web:
//                        String url = "https://vaymuon.vn/dieukhoannguoichovay/";
//                        if (!url.startsWith("https://") && !url.startsWith("http://")){
//                            url = "http://" + url;
//                        }
//                        Intent openUrlIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//                        startActivity(openUrlIntent);
//                        break;
//                    case R.id.drawer_item_exit:
//                        if (PrefUtils.getToken(BaseActivity.this) != null) {
//                            new AlertDialog.Builder(BaseActivity.this)
//                                    .setMessage("Bạn có muốn đăng xuất khỏi tài khoản này.")
//                                    .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
//                                        @Override
//                                        public void onClick(DialogInterface dialog, int which) {
//                                            taskLogoutBackGround(PrefUtils.getToken(BaseActivity.this));
//                                            logout();
//                                        }
//                                    })
//                                    .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
//                                        @Override
//                                        public void onClick(DialogInterface dialog, int which) {
//
//                                        }
//                                    })
//                                    .show();
//                        }
//                        break;
//                }
//                if (activityClass != null) {
//                    startActivity(new Intent(BaseActivity.this, activityClass));
//                    finish();
//                }
//                mItemToOpenWhenDrawerCloses = -1;
//            }
        }

        @Override
        public void onDrawerStateChanged(int newState) {
            if (mDrawerToggle != null) mDrawerToggle.onDrawerStateChanged(newState);
        }

        @Override
        public void onDrawerSlide(View drawerView, float slideOffset) {
            if (mDrawerToggle != null) mDrawerToggle.onDrawerSlide(drawerView, slideOffset);
        }

        @Override
        public void onDrawerOpened(View drawerView) {
            if (mDrawerToggle != null) mDrawerToggle.onDrawerOpened(drawerView);
//            if (getSupportActionBar() != null) getSupportActionBar()
//                    .setTitle(R.string.app_name);
        }
    };



    public void setDrawerEnabled(boolean enabled) {
        int lockMode = enabled ? DrawerLayout.LOCK_MODE_UNLOCKED :
                DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
        mDrawerLayout.setDrawerLockMode(lockMode);
        mDrawerToggle.setDrawerIndicatorEnabled(enabled);
    }

    public void addViewToolBar(View view) {
        mToolbar.addView(view, new Toolbar.LayoutParams(Gravity.END));
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (subcriptipon != null && !subcriptipon.isUnsubscribed())
            subcriptipon.unsubscribe();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(taskLogout != null && taskLogout.getStatus() != AsyncTask.Status.FINISHED){
            taskLogout.cancel(true);
        }
    }
}