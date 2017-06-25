package mycooking.funlife.com.vn.mycooking.ui.activitys;

import android.app.Fragment;
import android.os.Bundle;

import mycooking.funlife.com.vn.mycooking.ui.BaseActivity;
import mycooking.funlife.com.vn.mycooking.ui.fragments.HomeFragment;

public class HomeActivity extends BaseActivity {

    protected Fragment createRootFragment() {
        return  new HomeFragment();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}
