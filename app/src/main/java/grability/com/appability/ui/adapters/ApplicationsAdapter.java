package grability.com.appability.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import grability.com.appability.entities.Application;
import io.realm.RealmResults;

/**
 *
 * Created by juanangelardila on 4/1/16.
 */
public class ApplicationsAdapter extends RecyclerView.Adapter<ApplicationsViewHolder> implements View.OnClickListener{

    private OnItemClickListener onItemClickListener;
    private RealmResults<Application> applications;

    public ApplicationsAdapter(Context context, RealmResults<Application> applications) {
        this.applications = applications;
    }

    @Override
    public ApplicationsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        itemView.setOnClickListener(this);
        ApplicationsViewHolder viewHolder = new ApplicationsViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ApplicationsViewHolder viewHolder, int position) {
        Application application = applications.get(position);
        viewHolder.bindCategory(application);
    }

    @Override
    public int getItemCount() {
        return applications.size();
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
