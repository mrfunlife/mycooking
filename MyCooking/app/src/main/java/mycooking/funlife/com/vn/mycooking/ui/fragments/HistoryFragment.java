package mycooking.funlife.com.vn.mycooking.ui.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import mycooking.funlife.com.vn.mycooking.R;
import mycooking.funlife.com.vn.mycooking.interfaces.OpenDrawerLayout;
import mycooking.funlife.com.vn.mycooking.ui.BaseFragment;

/**
 * Created by funlife on 8/6/17.
 */

public class HistoryFragment extends BaseFragment {

    @BindView(R.id.iconLeft)
    ImageView iconLeft;
    @BindView(R.id.iconRight)
    ImageView iconRight;
    @BindView(R.id.btnLeft)
    Button btnLeft;
    @BindView(R.id.btnRight)
    Button btnRight;
    @BindView(R.id.bar_title)
    TextView txtTitle;

    private OpenDrawerLayout openDrawerLayout;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addcontrols();
    }

    private void addcontrols() {
        initBar();
    }

    private void initBar() {
        txtTitle.setTextColor(Color.WHITE);
        txtTitle.setText("Đồng Gia");
        openDrawerLayout = (OpenDrawerLayout)getActivity();
        iconLeft.setBackgroundResource(R.drawable.icon_menu_white);
        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(openDrawerLayout != null){
                    openDrawerLayout.openDrawerLayout();
                }
            }
        });


        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_history;
    }
}
