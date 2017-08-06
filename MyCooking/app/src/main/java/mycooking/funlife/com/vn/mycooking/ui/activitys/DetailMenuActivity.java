package mycooking.funlife.com.vn.mycooking.ui.activitys;

import android.app.Fragment;
import android.os.Bundle;

import mycooking.funlife.com.vn.mycooking.ui.BaseActivity;
import mycooking.funlife.com.vn.mycooking.ui.fragments.DetailMenuFragment;

public class DetailMenuActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected Fragment createRootFragment() {
        return new DetailMenuFragment();
    }
}
