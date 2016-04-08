package grability.com.appability.persistence;

import android.content.Context;

import org.json.JSONArray;


import grability.com.appability.entities.Application;
import grability.com.appability.entities.Category;
import grability.com.appability.network.AppsClient;
import grability.com.appability.utils.NetworkUtil;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 *
 * Created by juanangelardila on 4/2/16.
 */
public class DataManager {

    private AppsClient client = new AppsClient();
    private Context context;

    public DataManager(Context context) {
        this.context = context;
    }


    public void getData (final OnDataLoaded listener) {
        if(NetworkUtil.isNetworkAvailable(context)) {
            updateCacheData(new AppsClient.OnCallFinished() {
                @Override
                public void onSuccess(JSONArray categories, JSONArray applications) {
                    saveCache(categories, applications);
                    listener.dataLoaded(DataOrigin.NETWORK, null);
                }

                @Override
                public void onError() {
                    listener.dataFailed();
                }
            });
        } else {
            listener.dataLoaded(DataOrigin.CACHE, null);
        }
    }

    public void getCategories (final OnDataLoaded listener) {
        if(NetworkUtil.isNetworkAvailable(context)) {
            updateCacheData(new AppsClient.OnCallFinished() {
                @Override
                public void onSuccess(JSONArray categories, JSONArray applications) {
                    saveCache(categories, applications);
                    listener.dataLoaded(DataOrigin.NETWORK, getCategories());
                }

                @Override
                public void onError() {
                    listener.dataFailed();
                }
            });
        } else {
            listener.dataLoaded(DataOrigin.CACHE, getCategories());
        }
    }

    public void getApplications (final OnDataLoaded listener, final String categoryId) {
        if(NetworkUtil.isNetworkAvailable(context)) {
            updateCacheData(new AppsClient.OnCallFinished() {
                @Override
                public void onSuccess(JSONArray categories, JSONArray applications) {
                    saveCache(categories, applications);
                    listener.dataLoaded(DataOrigin.NETWORK, getApplications(categoryId));
                }

                @Override
                public void onError() {
                    listener.dataFailed();
                }
            });

        } else {
            listener.dataLoaded(DataOrigin.CACHE, getApplications(categoryId));
        }
    }


    private void updateCacheData (AppsClient.OnCallFinished clientCallback) {
        client.getData(clientCallback);
    }

    private void saveCache (JSONArray categories, JSONArray applications) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        try {
            realm.createOrUpdateAllFromJson(Application.class, applications);
            realm.createOrUpdateAllFromJson(Category.class, categories);
            realm.commitTransaction();
        } catch (Exception e) {
            realm.cancelTransaction();
        }
    }

    public RealmResults<Category> getCategories () {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(Category.class).findAllSorted(Category.NAME_FIELD);
    }

    public RealmResults<Application> getApplications (String categoryId) {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(Application.class)
                .equalTo(Category.ID_FIELD, categoryId)
                .findAllSorted(Application.NAME_FIELD);
    }

    public Application getApplication (String idApplication) {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(Application.class).equalTo(Application.ID_FIELD, idApplication).findFirst();
    }

    //region Listener
    public interface OnDataLoaded {
        void dataLoaded (DataOrigin origin, RealmResults data);
        void dataFailed ();
    }

    public enum DataOrigin {
        NETWORK, CACHE
    }

    //endregion

}
