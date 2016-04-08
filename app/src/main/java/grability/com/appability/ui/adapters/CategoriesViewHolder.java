package grability.com.appability.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import grability.com.appability.R;
import grability.com.appability.entities.Category;

/**
 *
 * Created by juanangelardila on 4/2/16.
 */
public class CategoriesViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.txvText)
    TextView categoryName;

    public CategoriesViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindCategory (Category category) {
        categoryName.setText(category.getName());
    }
}
