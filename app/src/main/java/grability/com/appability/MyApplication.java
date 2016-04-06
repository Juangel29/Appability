package grability.com.appability;

import android.app.Activity;
import android.app.Application;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import grability.com.appability.persistence.DataManager;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 *
 * Created by juanangelardila on 4/4/16.
 */
public class MyApplication extends Application implements Application.ActivityLifecycleCallbacks{

    private static MyApplication instance;
    private DataManager dataManagerInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        RealmConfiguration config = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(config);
        registerActivityLifecycleCallbacks(this);
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

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        if(getResources().getBoolean(R.bool.isTablet)){
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    @Override
    public void onActivityStarted(Activity activity) { }

    @Override
    public void onActivityResumed(Activity activity) { }

    @Override
    public void onActivityPaused(Activity activity) { }

    @Override
    public void onActivityStopped(Activity activity) { }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) { }

    @Override
    public void onActivityDestroyed(Activity activity) { }
}
