package mycooking.funlife.com.vn.mycooking.ui.fragments;


import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import mycooking.funlife.com.vn.mycooking.R;
import mycooking.funlife.com.vn.mycooking.adapter.DetailMenuAdapter;
import mycooking.funlife.com.vn.mycooking.model.DetailMenu;
import mycooking.funlife.com.vn.mycooking.ui.BaseFragment;

public class DetailMenuFragment extends BaseFragment {
    @BindView(R.id.btnBack)
    Button btnBack;
    @BindView(R.id.bar_title)
    TextView txtTitle;
    @BindView(R.id.detailMenuRecyclerView)
    RecyclerView recyclerView;
    private DetailMenuAdapter adapter;
    private List<DetailMenu> detailMenuList;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addControls();
    }

    private void addControls() {
        btnBack.setOnClickListener(v -> {
            getActivity().finish();
        });
        txtTitle.setText("Thực đơn số 5" + "" + "");

        detailMenuList = new ArrayList<>();
        adapter = new DetailMenuAdapter(getActivity(),detailMenuList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        demoData();
    }

    private void demoData() {
        for(int i = 0;i < 4;i++){
            DetailMenu detailMenu = new DetailMenu();
            detailMenu.setNameFood(getString(R.string.namefood));
            detailMenu.setMaterialFood(getString(R.string.material));
            detailMenu.setMakingFood(getString(R.string.making));
            detailMenu.setNoteFood(getString(R.string.notefood));
            detailMenuList.add(detailMenu);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_detail_menu;
    }
}
