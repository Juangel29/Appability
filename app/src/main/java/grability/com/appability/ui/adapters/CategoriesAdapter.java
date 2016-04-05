package grability.com.appability.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import grability.com.appability.entities.Category;
import io.realm.RealmResults;

/**
 *
 * Created by juanangelardila on 4/1/16.
 */
public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesViewHolder> implements RecyclerView.OnItemTouchListener{

    private GestureDetector gestureDetector;
    private OnItemClickListener onItemClickListener;

    private RealmResults<Category> categories;

    public CategoriesAdapter(Context context, RealmResults<Category> categories) {
        this.categories = categories;

//        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
//            @Override
//            public boolean onSingleTapUp(MotionEvent e) {
//                return true;
//            }
//        });
    }

    @Override
    public CategoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        CategoriesViewHolder viewHolder = new CategoriesViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CategoriesViewHolder viewHolder, int position) {
        Category category = categories.get(position);
        viewHolder.bindCategory(category);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }



    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View childView = rv.findChildViewUnder(e.getX(), e.getY());
        if (childView != null && onItemClickListener != null && gestureDetector.onTouchEvent(e)) {
            onItemClickListener.onItemClick(childView, rv.getChildAdapterPosition(childView));
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) { }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) { }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
