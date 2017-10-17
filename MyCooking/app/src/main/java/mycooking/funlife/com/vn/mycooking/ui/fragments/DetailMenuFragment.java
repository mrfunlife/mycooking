package mycooking.funlife.com.vn.mycooking.ui.fragments;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import mycooking.funlife.com.vn.mycooking.R;
import mycooking.funlife.com.vn.mycooking.adapter.DetailMenuAdapter;
import mycooking.funlife.com.vn.mycooking.model.DetailMenu;
import mycooking.funlife.com.vn.mycooking.ui.BaseFragment;

public class DetailMenuFragment extends BaseFragment {

    @BindView(R.id.detailMenuRecyclerView)
    RecyclerView recyclerView;
    private DetailMenuAdapter adapter;
    private List<DetailMenu> detailMenuList;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab_archive)
    FloatingActionButton fabArchive;
    @BindView(R.id.image_view_article)
    ImageView imgArticle;

    private boolean mSaved = true;
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addControls();
        setToolbar();
        fabArchive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mSaved){
                    MaterialDialog removeArticleDialog = new MaterialDialog.Builder(getActivity())
                            .content(R.string.use_menu)
                            .positiveText(R.string.ok)
                            .negativeText(R.string.cancel)
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {
                                    fabArchive.setImageResource(R.drawable.ic_check_white);
                                    mSaved = false;
                                }
                            }).build();
                    removeArticleDialog.show();
                }
            }
        });
    }


    private void setToolbar() {
        toolbar.setTitle("HN - 370");

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    private void addControls() {
//        btnBack.setOnClickListener(v -> {
//            getActivity().finish();
//        });
//        txtTitle.setText("Thực đơn số 5" + "" + "");

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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_detail_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //override default back button present on the toolbar
        if (id == android.R.id.home) {
            return true;
        }

        if (id == R.id.action_refresh) {

            return true;
        }

        if (id == R.id.action_open_in_browser) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.layout_detail_menu;
    }
}
