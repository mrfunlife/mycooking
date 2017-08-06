package mycooking.funlife.com.vn.mycooking.ui.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import mycooking.funlife.com.vn.mycooking.R;
import mycooking.funlife.com.vn.mycooking.adapter.MenuAdapter;
import mycooking.funlife.com.vn.mycooking.ui.BaseFragment;
import mycooking.funlife.com.vn.mycooking.ui.activitys.DetailMenuActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {

    private MenuAdapter adapter;
    private List<String> listMenu;
    private MaterialDialog mMaterialDialog;
    @BindView(R.id.homeRecyclerView)
    RecyclerView recyclerView;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showLoadingDialog(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                addControls();
                showLoadingDialog(false);
            }
        },2000);

    }

    private void addControls() {
        listMenu = new ArrayList<>();
        adapter = new MenuAdapter(listMenu,getActivity());
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        String menu = getString(R.string.fake_data_menu);
        listMenu.add(menu);
        listMenu.add(menu);
        listMenu.add(menu);
        listMenu.add(menu);
        listMenu.add(menu);
        listMenu.add(menu);
        adapter.notifyDataSetChanged();


        recyclerView.addOnItemTouchListener(new MenuAdapter.RecyclerTouchListener(getActivity(), recyclerView, new MenuAdapter.ClickListener() {
            @Override
            public void onClick(View view, int position) {
              startActivity(new Intent(getActivity(), DetailMenuActivity.class));
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_home;
    }
}
