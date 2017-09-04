package mycooking.funlife.com.vn.mycooking.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import mycooking.funlife.com.vn.mycooking.R;
import mycooking.funlife.com.vn.mycooking.model.Item;

/**
 * Created by funlife on 6/24/17.
 */

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder>{

        private Context context;
        private List<Item> listItem;

        public MenuAdapter(List<Item> listItem, Context context) {
            this.listItem = listItem;
            this.context = context;
        }

        @Override
        public MenuAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_menu_category,parent,false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MenuAdapter.MyViewHolder holder, int position) {
            Item item = listItem.get(position);
            holder.txtTitle.setText(item.getTitle());
            holder.txtBody.setText(item.getBody());
            Glide.with(holder.itemView.getContext())
                    .load(listItem.get(position).getImage())
                    .into(holder.image);
        }

        @Override
        public int getItemCount() {
            return listItem.size();
        }

public class MyViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.item_title)
    TextView txtTitle;
    @BindView(R.id.item_body)
    TextView txtBody;
    @BindView(R.id.item_image)
    CircleImageView image;
    public MyViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}

public interface ClickListener {
    void onClick(View view, int position);

    void onLongClick(View view, int position);
}

public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

    private GestureDetector gestureDetector;
    private MenuAdapter.ClickListener clickListener;

    public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final MenuAdapter.ClickListener clickListener) {
        this.clickListener = clickListener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (child != null && clickListener != null) {
                    clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                }
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

        View child = rv.findChildViewUnder(e.getX(), e.getY());
        if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
            clickListener.onClick(child, rv.getChildPosition(child));
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
}
