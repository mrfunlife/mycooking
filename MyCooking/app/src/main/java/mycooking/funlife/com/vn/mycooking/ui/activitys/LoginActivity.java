package mycooking.funlife.com.vn.mycooking.ui.activitys;

import android.app.Fragment;

import mycooking.funlife.com.vn.mycooking.ui.BaseActivity;
import mycooking.funlife.com.vn.mycooking.ui.fragments.LoginFragment;

/**
 * Created by funlife on 8/6/17.
 */

public class LoginActivity extends BaseActivity {
    @Override
    protected Fragment createRootFragment() {
        return new LoginFragment();
    }
}
