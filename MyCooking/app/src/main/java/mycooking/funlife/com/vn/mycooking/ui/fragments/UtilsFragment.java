package mycooking.funlife.com.vn.mycooking.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import mycooking.funlife.com.vn.mycooking.R;
import mycooking.funlife.com.vn.mycooking.adapter.HistoryMenuAdapter;
import mycooking.funlife.com.vn.mycooking.adapter.UtilsAdapter;
import mycooking.funlife.com.vn.mycooking.interfaces.OpenDrawerLayout;
import mycooking.funlife.com.vn.mycooking.model.HistoryMenu;
import mycooking.funlife.com.vn.mycooking.model.Utils;
import mycooking.funlife.com.vn.mycooking.ui.BaseFragment;

/**
 * Created by funlife on 8/6/17.
 */

public class UtilsFragment extends BaseFragment {
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


    @BindView(R.id.utilsRecylerView)
    RecyclerView recyclerView;

    private List<Utils> listUtils;
    private UtilsAdapter adapter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addControls();
        demoData();
    }

    private void demoData() {
        listUtils = new ArrayList<>();
        adapter = new UtilsAdapter(getActivity(),listUtils);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        fake();
    }

    private void fake() {
        Utils utils1 = new Utils("Giá thực phẩm mỗi ngày");
        Utils utils2 = new Utils("Dinh dưỡng cho bé");
        Utils utils3 = new Utils("Mẹo vặt gia đình");
        Utils utils4 = new Utils("Khác");
        listUtils.add(utils1);
        listUtils.add(utils2);
        listUtils.add(utils3);
        listUtils.add(utils4);
        adapter.notifyDataSetChanged();
    }

    private void addControls() {
        initBar();
    }

    private void initBar() {
        txtTitle.setText("Giúp Ích Mỗi Ngày");
        openDrawerLayout = (OpenDrawerLayout)getActivity();
        iconLeft.setBackgroundResource(R.drawable.icon_menu_color);
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
        return R.layout.fragment_untils;
    }
}
