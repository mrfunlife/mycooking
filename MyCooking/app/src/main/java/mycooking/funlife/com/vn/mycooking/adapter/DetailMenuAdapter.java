package mycooking.funlife.com.vn.mycooking.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mycooking.funlife.com.vn.mycooking.R;
import mycooking.funlife.com.vn.mycooking.model.DetailMenu;

/**
 * Created by funlife on 6/25/17.
 */

public class DetailMenuAdapter extends RecyclerView.Adapter<DetailMenuAdapter.MyViewHolder> {
    private Context mContext;
    private List<DetailMenu> listDetailMenu;


    public DetailMenuAdapter(Context mContext, List<DetailMenu> listDetailMenu) {
        this.mContext = mContext;
        this.listDetailMenu = listDetailMenu;
    }

    @Override
    public DetailMenuAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_detail_menu,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DetailMenuAdapter.MyViewHolder holder, int position) {
        DetailMenu detailMenu = listDetailMenu.get(position);
        holder.txtMaterial.setText(detailMenu.getMaterialFood());
        holder.txtNameFood.setText(detailMenu.getNameFood());
        holder.txtMaking.setText(detailMenu.getMakingFood());
        holder.txtNoteFood.setText(detailMenu.getNoteFood());
    }

    @Override
    public int getItemCount() {
        return listDetailMenu.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.itemDetailMenuNameFood)
        TextView txtNameFood;
        @BindView(R.id.itemDetailMenuMaterial)
        TextView txtMaterial;
        @BindView(R.id.itemDetailMenuMaking)
        TextView txtMaking;
        @BindView(R.id.itemDetailNoteFood)
        TextView txtNoteFood;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
