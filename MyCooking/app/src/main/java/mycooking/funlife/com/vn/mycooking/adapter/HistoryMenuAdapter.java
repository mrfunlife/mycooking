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
import mycooking.funlife.com.vn.mycooking.model.HistoryMenu;

/**
 * Created by funlife on 9/6/17.
 */

public class HistoryMenuAdapter extends RecyclerView.Adapter<HistoryMenuAdapter.MyViewHolder> {
    private Context mContext;
    private List<HistoryMenu> listHistoryMenu;
    private String food ="";


    public HistoryMenuAdapter(Context mContext, List<HistoryMenu> listHistoryMenu) {
        this.mContext = mContext;
        this.listHistoryMenu = listHistoryMenu;
    }



    @Override
    public HistoryMenuAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_history_menu,parent,false);
        return new HistoryMenuAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HistoryMenuAdapter.MyViewHolder holder, int position) {
        HistoryMenu historyMenu = listHistoryMenu.get(position);
        holder.txtMenuTitle.setText(historyMenu.getTitleMenu());
        holder.txtDateUse.setText(historyMenu.getDateUseMenu());
        holder.txtFood.setText(historyMenu.getListFood());

    }

    @Override
    public int getItemCount() {
        return listHistoryMenu.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.historyMenuTitle)
        TextView txtMenuTitle;
        @BindView(R.id.historyMenuDateUse)
        TextView txtDateUse;
        @BindView(R.id.historyMenuFood)
        TextView txtFood;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
