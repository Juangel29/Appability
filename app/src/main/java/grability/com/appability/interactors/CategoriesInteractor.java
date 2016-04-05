package grability.com.appability.interactors;

import android.content.Context;

import grability.com.appability.interfaces.OnCategoriesFinishedListener;
import grability.com.appability.persistence.DataManager;
import io.realm.RealmResults;

/**
 *
 * Created by juanangelardila on 4/4/16.
 */
public class CategoriesInteractor {

    private OnCategoriesFinishedListener listener;
    private DataManager dataManager;

    public CategoriesInteractor(Context context, OnCategoriesFinishedListener listener) {
        this.listener = listener;
        dataManager = new DataManager(context);
    }

    public void getCategories () {
        dataManager.getCategories(new DataManager.OnDataLoaded() {
            @Override
            public void dataLoaded(DataManager.DataOrigin origin, RealmResults data) {
                listener.onLoadCategoriesSuccess(origin, data);
            }

            @Override
            public void dataFailed() {
                listener.onLoadFailure();
            }
        });
    }

}
