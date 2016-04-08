package grability.com.appability.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import grability.com.appability.R;
import grability.com.appability.entities.Application;

/**
 *
 * Created by juanangelardila on 4/2/16.
 */
public class ApplicationsViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.imvImage)
    ImageView imvApplicationImage;

    @Bind(R.id.txvName)
    TextView txvName;

    @Bind(R.id.txvArtist)
    TextView txvArtist;

    @Bind(R.id.txvRights)
    TextView txvRights;

    public ApplicationsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindCategory (Context context, Application application) {
        Picasso.with(context).load(application.getImageUrl()).into(imvApplicationImage);
        txvName.setText(application.getName());
        txvArtist.setText(application.getArtist());
        txvRights.setText(application.getRights());
    }
}
