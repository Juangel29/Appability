package grability.com.appability.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import grability.com.appability.MyApplication;
import grability.com.appability.R;
import grability.com.appability.interfaces.ICategories;
import grability.com.appability.entities.Category;
import grability.com.appability.persistence.DataManager;
import grability.com.appability.presenters.CategoryPresenter;
import grability.com.appability.ui.adapters.CategoriesAdapter;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;


public class CategoriesFragment extends Fragment implements ICategories{

    @Bind(R.id.rvCategories) RecyclerView rvCategories;

    private CategoriesAdapter categoriesAdapter;
    private RealmResults<Category> categories;

    private CategoryPresenter categoryPresenter;

    private OnFragmentInteractionListener listener;

    public CategoriesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        ButterKnife.bind(this, view);

        categoryPresenter = new CategoryPresenter(getContext(), this);
        initCategoriesAdapter();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        categoryPresenter.loadCategories();
    }


    private void initCategoriesAdapter() {
        categories = MyApplication.getInstance().getDataManagerInstance().getCategories();
        categoriesAdapter = new CategoriesAdapter(categories);
        categoriesAdapter.setOnItemClickListener(new CategoriesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view) {
                int position = rvCategories.getChildAdapterPosition(view);
                listener.onCategorySelected(view, categories.get(position));
            }
        });

        rvCategories.setLayoutManager(getResources().getBoolean(R.bool.isTablet) ? new GridLayoutManager(getContext(), 5) : new LinearLayoutManager(getContext()));
        rvCategories.setAdapter(categoriesAdapter);
        RealmChangeListener realmChangeListener = new RealmChangeListener() {
            @Override
            public void onChange() {
                categoriesAdapter.notifyDataSetChanged();
            }
        };
        rvCategories.setHasFixedSize(true);
        rvCategories.setItemAnimator(new DefaultItemAnimator());
        categories.addChangeListener(realmChangeListener);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void onCategoriesLoaded(DataManager.DataOrigin origin, RealmResults<Category> categories) {
        if (origin == DataManager.DataOrigin.CACHE) {
            Snackbar.make(rvCategories, getString(R.string.no_internet_connection_message), Snackbar.LENGTH_INDEFINITE).show();
        }
    }

    public interface OnFragmentInteractionListener {
        void onCategorySelected(View view, Category category);
    }
}
