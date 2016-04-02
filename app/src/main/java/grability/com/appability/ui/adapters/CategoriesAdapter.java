package grability.com.appability.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import grability.com.appability.models.Category;
import io.realm.RealmResults;

/**
 *
 * Created by juanangelardila on 4/1/16.
 */
public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesViewHolder> {

    private RealmResults<Category> categories;

    public CategoriesAdapter(RealmResults<Category> categories) {
        this.categories = categories;
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
}
