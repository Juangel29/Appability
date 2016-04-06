package grability.com.appability.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import grability.com.appability.R;
import grability.com.appability.ui.fragments.CategoriesFragment;

public class MainActivity extends AppCompatActivity implements CategoriesFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public void onCategoryClicked(String categoryId) {
        Intent intent = new Intent(this, ApplicationsActivity.class);
        intent.putExtra("categoryId", categoryId);
        startActivity(intent);
    }
}
