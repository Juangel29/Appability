package grability.com.appability.presenters;

import android.content.Context;

import grability.com.appability.interactors.CategoriesInteractor;
import grability.com.appability.interfaces.ICategories;
import grability.com.appability.interfaces.ICategoriesPresenter;
import grability.com.appability.interfaces.OnCategoriesFinishedListener;
import grability.com.appability.entities.Category;
import grability.com.appability.persistence.DataManager;
import io.realm.RealmResults;

/**
 *
 * Created by juanangelardila on 4/4/16.
 */
public class CategoryPresenter implements OnCategoriesFinishedListener, ICategoriesPresenter{

    private ICategories view;
    private CategoriesInteractor categoriesInteractor;

    public CategoryPresenter(Context context, ICategories view) {
        this.view = view;
        categoriesInteractor = new CategoriesInteractor(context, this);
    }

    @Override
    public void onLoadCategoriesSuccess(DataManager.DataOrigin origin, RealmResults<Category> categories) {
        view.onCategoriesLoaded(origin, categories);
    }

    @Override
    public void onLoadFailure() {

    }

    @Override
    public void loadCategories() {
        categoriesInteractor.getCategories();
    }
}
