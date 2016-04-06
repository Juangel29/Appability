package grability.com.appability.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import grability.com.appability.entities.Application;
import grability.com.appability.entities.Category;

/**
 *
 * Created by juanangelardila on 4/2/16.
 */
public class ApplicationsViewHolder extends RecyclerView.ViewHolder {

    @Bind(android.R.id.text1)
    TextView applicationName;

    public ApplicationsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindCategory (Application application) {
        applicationName.setText(application.getName());
    }
}
