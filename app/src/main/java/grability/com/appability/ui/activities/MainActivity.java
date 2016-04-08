package grability.com.appability.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import grability.com.appability.R;
import grability.com.appability.entities.Category;
import grability.com.appability.ui.fragments.CategoriesFragment;

public class MainActivity extends AppCompatActivity implements CategoriesFragment.OnFragmentInteractionListener {

    public final static String EXTRA_CATEGORY_ID = "categoryId";
    public final static String EXTRA_CATEGORY_TITLE = "title";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public void onCategorySelected(View view, Category category) {
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this);
        Intent intent = new Intent(this, ApplicationsActivity.class);
        intent.putExtra(EXTRA_CATEGORY_ID, category.getId());
        intent.putExtra(EXTRA_CATEGORY_TITLE, category.getName());
        ActivityCompat.startActivity(this, intent, options.toBundle());
    }
}
