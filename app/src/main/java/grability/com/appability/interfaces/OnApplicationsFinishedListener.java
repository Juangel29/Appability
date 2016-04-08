package grability.com.appability.interfaces;

import grability.com.appability.entities.Application;
import grability.com.appability.persistence.DataManager;
import io.realm.RealmResults;

/**
 *
 * Created by juanangelardila on 4/4/16.
 */
public interface OnApplicationsFinishedListener {
    void onLoadApplicationsSuccess(DataManager.DataOrigin origin, RealmResults<Application> applications);
    void onLoadFailure();
}
