package grability.com.appability.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import grability.com.appability.MyApplication;
import grability.com.appability.R;
import grability.com.appability.entities.Application;
import grability.com.appability.interfaces.IApplications;
import grability.com.appability.persistence.DataManager;
import grability.com.appability.presenters.ApplicationPresenter;
import grability.com.appability.ui.adapters.ApplicationsAdapter;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

import static grability.com.appability.ui.activities.MainActivity.*;


public class ApplicationsFragment extends Fragment implements IApplications{


    @Bind(R.id.rvApplications)
    RecyclerView rvApplications;

    private ApplicationsAdapter applicationsAdapter;
    private RealmResults<Application> applications;

    private ApplicationPresenter applicationPresenter;

    private OnFragmentInteractionListener listener;

    private String categoryId;

    public ApplicationsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_applications, container, false);
        ButterKnife.bind(this, view);
        applicationPresenter = new ApplicationPresenter(getContext(), this);
        initApplicationsAdapter();
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(getActivity().getIntent().getExtras() != null) {
            categoryId = getActivity().getIntent().getStringExtra(EXTRA_CATEGORY_ID);
        }
        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        applicationPresenter.loadApplications(categoryId);
    }

    private void initApplicationsAdapter() {
        applications = MyApplication.getInstance().getDataManagerInstance().getApplications(categoryId);
        applicationsAdapter = new ApplicationsAdapter(getContext(), applications);
        applicationsAdapter.setOnItemClickListener(new ApplicationsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view) {
                listener.onApplicationSelected(view, applications.get(rvApplications.getChildAdapterPosition(view)));
            }
        });

        rvApplications.setLayoutManager(new LinearLayoutManager(getContext()));
        rvApplications.setAdapter(applicationsAdapter);
        RealmChangeListener realmChangeListener = new RealmChangeListener() {
            @Override
            public void onChange() {
                applicationsAdapter.notifyDataSetChanged();
            }
        };
        applications.addChangeListener(realmChangeListener);
        if(MyApplication.getInstance().isTablet()) {
            if (!applications.isEmpty()) {
                listener.onApplicationSelected(null, applications.first());
            }
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void onApplicationsLoaded(DataManager.DataOrigin origin, RealmResults<Application> applications) {
        if (origin == DataManager.DataOrigin.CACHE) {
            Snackbar.make(rvApplications, getString(R.string.no_internet_connection_message), Snackbar.LENGTH_INDEFINITE).show();
        }
    }

    public interface OnFragmentInteractionListener {
        void onApplicationSelected(View view, Application application);
    }
}
