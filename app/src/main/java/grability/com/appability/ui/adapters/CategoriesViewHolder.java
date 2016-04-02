package grability.com.appability.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import grability.com.appability.models.Category;

/**
 *
 * Created by juanangelardila on 4/2/16.
 */
public class CategoriesViewHolder extends RecyclerView.ViewHolder {

    @Bind(android.R.id.text1)
    TextView categoryName;

    public CategoriesViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindCategory (Category category) {
        categoryName.setText(category.getLabel());
    }
}
