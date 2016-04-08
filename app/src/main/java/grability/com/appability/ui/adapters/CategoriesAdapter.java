package grability.com.appability.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import grability.com.appability.R;
import grability.com.appability.entities.Category;
import io.realm.RealmResults;

/**
 *
 * Created by juanangelardila on 4/1/16.
 */
public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesViewHolder> implements View.OnClickListener{

    private OnItemClickListener onItemClickListener;

    private RealmResults<Category> categories;

    public CategoriesAdapter(RealmResults<Category> categories) {
        this.categories = categories;
    }

    @Override
    public CategoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        itemView.setOnClickListener(this);
        return new CategoriesViewHolder(itemView);
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
    public void onClick(View v) {
        if(onItemClickListener != null) {
            onItemClickListener.onItemClick(v);
        }
    }


    public interface OnItemClickListener {
        void onItemClick(View view);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
