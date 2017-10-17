package mycooking.funlife.com.vn.mycooking.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mycooking.funlife.com.vn.mycooking.R;
import mycooking.funlife.com.vn.mycooking.model.HistoryMenu;
import mycooking.funlife.com.vn.mycooking.model.Utils;

/**
 * Created by student on 06/10/2017.
 */

public class UtilsAdapter extends RecyclerView.Adapter<UtilsAdapter.MyViewHolder> {
    private Context mContext;
    private List<Utils> listUtils;
    private String food ="";


    public UtilsAdapter(Context mContext, List<Utils> listUtils) {
        this.mContext = mContext;
        this.listUtils = listUtils;
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_utils,parent,false);
        return new UtilsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UtilsAdapter.MyViewHolder holder, int position) {
        Utils utils = listUtils.get(position);
        holder.txtName.setText(utils.getName());
        holder.iconItem.setImageResource(utils.getImg());
    }

    @Override
    public int getItemCount() {
        return listUtils.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_utils_name)
        TextView txtName;
        @BindView(R.id.item_utils_img)
        ImageView iconItem;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

