package grability.com.appability.ui.activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import grability.com.appability.R;
import grability.com.appability.ui.fragments.ApplicationsFragment;

public class ApplicationsActivity extends AppCompatActivity implements ApplicationsFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applications);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
