package grability.com.appability.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import grability.com.appability.MyApplication;
import grability.com.appability.R;
import grability.com.appability.entities.Application;
import grability.com.appability.ui.activities.ApplicationsActivity;

import static grability.com.appability.ui.activities.ApplicationsActivity.ARG_APP_ID;

/**
 * A simple {@link Fragment} subclass.
 */
public class ApplicationDetailFragment extends Fragment {

    @Bind(R.id.imvImage) ImageView imvImage;
    @Bind(R.id.txvName) TextView txvName;
    @Bind(R.id.txvArtist) TextView txvArtist;
    @Bind(R.id.txvRights) TextView txvRights;
    @Bind(R.id.txvCategory) TextView txvCategory;
    @Bind(R.id.txvReleaseDate) TextView txvReleaseDate;
    @Bind(R.id.txvSummary) TextView txvSummary;

    private Application application;

    public ApplicationDetailFragment() { }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_application_detail, container, false);
        ButterKnife.bind(this, view);

        Bundle arguments = getArguments();
        String applicationId = arguments.getString(ARG_APP_ID);
        application = MyApplication.getInstance().getDataManagerInstance().getApplication(applicationId);
        initApplicationDetail();

        ViewCompat.setTransitionName(imvImage, getString(R.string.transition_image));
        ViewCompat.setTransitionName(txvName,  getString(R.string.transition_name));
        ViewCompat.setTransitionName(txvArtist, getString(R.string.transition_artist));
        ViewCompat.setTransitionName(txvRights, getString(R.string.transition_rights));

        return view;
    }

    public void initApplicationDetail () {
        Picasso.with(getContext()).load(application.getImageUrl()).into(imvImage);
        txvName.setText(application.getName());
        txvArtist.setText(application.getArtist());
        txvRights.setText(application.getRights());
        txvCategory.setText(getString(R.string.category, application.getCategory().getName()));
        txvReleaseDate.setText(getString(R.string.release_date, application.getReleaseDate()));
        txvSummary.setText(application.getSummary());
    }

}
