package grability.com.appability;

import android.app.Application;

import grability.com.appability.persistence.DataManager;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 *
 * Created by juanangelardila on 4/4/16.
 */
public class MyApplication extends Application {

    private static MyApplication instance;
    private DataManager dataManagerInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        RealmConfiguration config = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(config);
    }

    public static MyApplication getInstance () {
        return instance;
    }

    public DataManager getDataManagerInstance () {
        if(dataManagerInstance == null) {
            dataManagerInstance = new DataManager(getApplicationContext());
        }
        return dataManagerInstance;
    }

}
