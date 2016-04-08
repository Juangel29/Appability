package grability.com.appability.ui.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Gravity;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;
import grability.com.appability.MyApplication;
import grability.com.appability.R;
import grability.com.appability.entities.Application;
import grability.com.appability.ui.fragments.ApplicationDetailFragment;
import grability.com.appability.ui.fragments.ApplicationsFragment;

import static grability.com.appability.ui.activities.MainActivity.EXTRA_CATEGORY_TITLE;

public class ApplicationsActivity extends AppCompatActivity implements ApplicationsFragment.OnFragmentInteractionListener {

    public final static String ARG_APP_ID = "appId";

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private ApplicationsFragment applicationsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applications);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        if (getIntent().getExtras() != null) {
            toolbar.setSubtitle(getIntent().getStringExtra(EXTRA_CATEGORY_TITLE));
        }

        if (MyApplication.getInstance().isTablet()) {
            applicationsFragment = (ApplicationsFragment) getSupportFragmentManager().findFragmentById(R.id.frApplications);
        } else {
            applicationsFragment = new ApplicationsFragment();
            showFragment(applicationsFragment, false, false, null);
        }
    }

    private void showFragment(Fragment fragment, boolean replace, boolean addToBackStack, View view) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (replace) {
            fragmentTransaction.replace(R.id.frlContent, fragment, fragment.getClass().getName());
        }
        else {
            getSupportFragmentManager().executePendingTransactions();
            if (!fragment.isAdded()) {
                fragmentTransaction.add(R.id.frlContent, fragment, fragment.getClass().getName());
            }
        }
        if(addToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }

        if (view != null &&  Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ViewCompat.setTransitionName(view.findViewById(R.id.imvImage), getString(R.string.transition_image));
            ViewCompat.setTransitionName(view.findViewById(R.id.txvName), getString(R.string.transition_name));
            ViewCompat.setTransitionName(view.findViewById(R.id.txvArtist), getString(R.string.transition_artist));
            ViewCompat.setTransitionName(view.findViewById(R.id.txvRights), getString(R.string.transition_rights));

            fragmentTransaction.addSharedElement(view.findViewById(R.id.imvImage), getString(R.string.transition_image));
            fragmentTransaction.addSharedElement(view.findViewById(R.id.txvName), getString(R.string.transition_name));
            fragmentTransaction.addSharedElement(view.findViewById(R.id.txvArtist), getString(R.string.transition_artist));
            fragmentTransaction.addSharedElement(view.findViewById(R.id.txvRights), getString(R.string.transition_rights));

            Transition move = TransitionInflater.from(this).inflateTransition(android.R.transition.move);
            Transition top = TransitionInflater.from(this).inflateTransition(android.R.transition.slide_top);

            Slide slideTransition = new Slide(Gravity.RIGHT);
            slideTransition.setDuration(1000);
            fragment.setEnterTransition(slideTransition);
            fragment.setSharedElementEnterTransition(move);
            fragment.setSharedElementReturnTransition(top);

            Slide slideOutTransition = new Slide(Gravity.LEFT);
            slideTransition.setDuration(500);
            applicationsFragment.setExitTransition(slideOutTransition);

            fragment.setAllowEnterTransitionOverlap(false);
            fragment.setAllowReturnTransitionOverlap(false);
            applicationsFragment.setAllowReturnTransitionOverlap(false);

        }

        fragmentTransaction.commit();
    }

    @Override
    public void onApplicationSelected(View view, Application application) {
        Bundle arguments = new Bundle();
        arguments.putString(ARG_APP_ID, application.getId());
        ApplicationDetailFragment applicationDetailFragment = new ApplicationDetailFragment();
        applicationDetailFragment.setArguments(arguments);
        showFragment(applicationDetailFragment, true, !MyApplication.getInstance().isTablet(), view);
    }
}
