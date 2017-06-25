package mycooking.funlife.com.vn.mycooking.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mycooking.funlife.com.vn.mycooking.R;

/**
 * Created by funlife on 6/24/17.
 */

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder>{

        private List<String> listMenu;
        private Context context;

        public MenuAdapter(List<String> listMenu, Context context) {
            this.listMenu = listMenu;
            this.context = context;
        }

        @Override
        public MenuAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_menu,parent,false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MenuAdapter.MyViewHolder holder, int position) {
//            String imgAvatar = listIdcard.get(position);
//            if(!imgAvatar.isEmpty()) {
//                Picasso.with(context).load(imgAvatar).into(holder.imgAvatar);
//            }
            String menu = listMenu.get(position);
            holder.txtMenu.setText(menu);
        }

        @Override
        public int getItemCount() {
            return listMenu.size();
        }

public class MyViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.itemHomeTxtMenu)
    TextView txtMenu;
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
