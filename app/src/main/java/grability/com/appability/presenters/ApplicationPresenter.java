package grability.com.appability.presenters;

import android.content.Context;

import grability.com.appability.entities.Application;
import grability.com.appability.entities.Category;
import grability.com.appability.interactors.ApplicationsInteractor;
import grability.com.appability.interactors.CategoriesInteractor;
import grability.com.appability.interfaces.IApplications;
import grability.com.appability.interfaces.IApplicationsPresenter;
import grability.com.appability.interfaces.ICategories;
import grability.com.appability.interfaces.ICategoriesPresenter;
import grability.com.appability.interfaces.OnApplicationsFinishedListener;
import grability.com.appability.interfaces.OnCategoriesFinishedListener;
import grability.com.appability.persistence.DataManager;
import io.realm.RealmResults;

/**
 *
 * Created by juanangelardila on 4/4/16.
 */
public class ApplicationPresenter implements OnApplicationsFinishedListener, IApplicationsPresenter{

    private IApplications view;
    private ApplicationsInteractor categoriesInteractor;

    public ApplicationPresenter(Context context, IApplications view) {
        this.view = view;
        categoriesInteractor = new ApplicationsInteractor(context, this);
    }

    @Override
    public void onLoadApplicationsSuccess(DataManager.DataOrigin origin, RealmResults<Application> applications) {
        view.onApplicationsLoaded(origin, applications);
    }

    @Override
    public void onLoadFailure() {

    }

    @Override
    public void loadApplications(String categoryId, String searchTerm) {
        categoriesInteractor.getApplications(categoryId, searchTerm);
    }
}
