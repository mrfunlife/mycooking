package mycooking.funlife.com.vn.mycooking.ui.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import mycooking.funlife.com.vn.mycooking.R;
import mycooking.funlife.com.vn.mycooking.adapter.MenuAdapter;
import mycooking.funlife.com.vn.mycooking.customview.DiscreteScrollView.DiscreteScrollView;
import mycooking.funlife.com.vn.mycooking.customview.DiscreteScrollView.Orientation;
import mycooking.funlife.com.vn.mycooking.customview.DiscreteScrollView.transform.ScaleTransformer;
import mycooking.funlife.com.vn.mycooking.interfaces.OpenDrawerLayout;
import mycooking.funlife.com.vn.mycooking.model.Item;
import mycooking.funlife.com.vn.mycooking.ui.BaseFragment;
import mycooking.funlife.com.vn.mycooking.ui.activitys.DetailMenuActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener{



    @BindView(R.id.homeRecyclerView)
    DiscreteScrollView discreteScrollView;
    @BindView(R.id.homeBtnFloatSelect)
    FloatingActionButton floatSelect;
    @BindView(R.id.homeBtnStar)
    ImageButton btnStar;

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
    private List<Item> itemList;
    private MenuAdapter adapter;
    Item itemStar;


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
        initBar();
        btnStar.setOnClickListener(this);
        floatSelect.setOnClickListener(this);
        itemStar = new Item();

        itemList = new ArrayList<>();
        Item item1 = new Item(1,getString(R.string.title_menu),getString(R.string.fake_data_menu),R.drawable.shop1,false);
        Item item2 = new Item(2,getString(R.string.title_menu),getString(R.string.fake_data_menu),R.drawable.shop2,true);
        Item item3 = new Item(3,getString(R.string.title_menu),getString(R.string.fake_data_menu),R.drawable.shop3,false);
        Item item4 = new Item(4,getString(R.string.title_menu),getString(R.string.fake_data_menu),R.drawable.shop4,true);
        Item item5 = new Item(5,getString(R.string.title_menu),getString(R.string.fake_data_menu),R.drawable.shop5,false);
        Item item6 = new Item(6,getString(R.string.title_menu),getString(R.string.fake_data_menu),R.drawable.shop6,true);
        itemList.add(item1);
        itemList.add(item2);
        itemList.add(item3);
        itemList.add(item4);
        itemList.add(item5);
        itemList.add(item6);


        adapter  = new MenuAdapter(itemList,getActivity());
        discreteScrollView.setOrientation(Orientation.HORIZONTAL);
        discreteScrollView.setAdapter(adapter);
        discreteScrollView.setItemTransformer(new ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .build());
        adapter.notifyDataSetChanged();


        discreteScrollView.addOnItemTouchListener(new MenuAdapter.RecyclerTouchListener(getActivity(), discreteScrollView, new MenuAdapter.ClickListener() {
            @Override
            public void onClick(View view, int position) {
              startActivity(new Intent(getActivity(), DetailMenuActivity.class));
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        discreteScrollView.setOnItemChangedListener(new DiscreteScrollView.OnItemChangedListener<RecyclerView.ViewHolder>() {
            @Override
            public void onCurrentItemChanged(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
                itemStar = itemList.get(position);
                onItemChanged(itemStar);
            }
        });
    }

    private void onItemChanged(Item item) {
        changeRateButtonState(item);
    }

    private void changeRateButtonState(Item item) {
        if (item.isStarSave()) {
            btnStar.setImageResource(R.drawable.ic_star_black_24dp);
            btnStar.setColorFilter(ContextCompat.getColor(getActivity(), R.color.maincolor));
        } else {
            btnStar.setImageResource(R.drawable.ic_star_border_black_24dp);
            btnStar.setColorFilter(ContextCompat.getColor(getActivity(), R.color.color_black));
        }
    }

    private void initBar() {
        txtTitle.setText("Danh sách thực đơn");
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
        return R.layout.fragment_home;
    }

    @Override
    public void onClick(View v) {
        if(v == floatSelect){
            startActivity(new Intent(getActivity(), DetailMenuActivity.class));
        }
        if(v == btnStar){
            if(itemStar.isStarSave()){
                itemStar.setStarSave(false);
                btnStar.setImageResource(R.drawable.ic_star_border_black_24dp);
                btnStar.setColorFilter(ContextCompat.getColor(getActivity(), R.color.color_black));
            }else {
                btnStar.setImageResource(R.drawable.ic_star_black_24dp);
                itemStar.setStarSave(true);
                btnStar.setColorFilter(ContextCompat.getColor(getActivity(), R.color.maincolor));
            }
        }
    }
}
