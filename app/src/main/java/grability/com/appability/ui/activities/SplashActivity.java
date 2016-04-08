package grability.com.appability.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

import grability.com.appability.MyApplication;
import grability.com.appability.R;
import grability.com.appability.persistence.DataManager;
import io.realm.RealmResults;


public class SplashActivity extends AppCompatActivity {


    private static final long SPLASH_SCREEN_DELAY = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        MyApplication.getInstance().getDataManagerInstance().getData(new DataManager.OnDataLoaded() {
            @Override
            public void dataLoaded(DataManager.DataOrigin origin, RealmResults data) {
                gotoMainActivity();
            }

            @Override
            public void dataFailed() {

            }
        });
    }

    private void gotoMainActivity () {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);
    }



}
