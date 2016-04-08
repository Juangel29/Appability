package grability.com.appability.interactors;

import android.content.Context;

import grability.com.appability.interfaces.OnApplicationsFinishedListener;
import grability.com.appability.persistence.DataManager;
import io.realm.RealmResults;

/**
 *
 * Created by juanangelardila on 4/4/16.
 */
public class ApplicationsInteractor {

    private OnApplicationsFinishedListener listener;
    private DataManager dataManager;

    public ApplicationsInteractor(Context context, OnApplicationsFinishedListener listener) {
        this.listener = listener;
        dataManager = new DataManager(context);
    }

    public void getApplications (String categoryId) {
        dataManager.getApplications(new DataManager.OnDataLoaded() {
            @Override
            public void dataLoaded(DataManager.DataOrigin origin, RealmResults data) {
                listener.onLoadApplicationsSuccess(origin, data);
            }

            @Override
            public void dataFailed() {
                listener.onLoadFailure();
            }
        }, categoryId);
    }

}
