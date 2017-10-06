package mycooking.funlife.com.vn.mycooking.ui.fragments;

import android.graphics.Color;
import android.os.Bundle;
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
import mycooking.funlife.com.vn.mycooking.interfaces.OpenDrawerLayout;
import mycooking.funlife.com.vn.mycooking.model.HistoryMenu;
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
    String food = "";

    @BindView(R.id.historyMenuRecylerView)
    RecyclerView recyclerView;

    private List<HistoryMenu> listHistoryMenu;
    private HistoryMenuAdapter adapter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addcontrols();
    }

    private void addcontrols() {
        initBar();
        demoData();
    }

    private void demoData() {
        listHistoryMenu = new ArrayList<>();
        adapter = new HistoryMenuAdapter(getActivity(),listHistoryMenu);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        fakeData();
    }

    private void fakeData() {
        List<String> listFood = new ArrayList<>();
        listFood.add("Đùi gà chiên");
        listFood.add("Rau cải luộc");
        listFood.add("Khoai tây băm nhỏ");
        listFood.add("Cá rán mỡ hành");

        for(String s: listFood){
            food = food + "- " + s + "\n";
        }

        HistoryMenu historyMenu1 = new HistoryMenu("","Thực đơn: HN470","Thú 3 - 05/09/2017",food);
        HistoryMenu historyMenu2 = new HistoryMenu("","Thực đơn: HN470","Thú 3 - 05/09/2017",food);
        HistoryMenu historyMenu3 = new HistoryMenu("","Thực đơn: HN470","Thú 3 - 05/09/2017",food);
        HistoryMenu historyMenu4 = new HistoryMenu("","Thực đơn: HN470","Thú 3 - 05/09/2017",food);
        HistoryMenu historyMenu5 = new HistoryMenu("","Thực đơn: HN470","Thú 3 - 05/09/2017",food);

        listHistoryMenu.add(historyMenu1);
        listHistoryMenu.add(historyMenu2);
        listHistoryMenu.add(historyMenu3);
        listHistoryMenu.add(historyMenu4);
        listHistoryMenu.add(historyMenu5);
        adapter.notifyDataSetChanged();
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
