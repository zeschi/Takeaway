package com.zes.bundle.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by zhy on 15/8/25.
 */
public class MyApplication extends Application {

    private static MyApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }


    /**
     * @return application instance
     */
    public static /*synchronized*/ MyApplication getInstance() {
        return sInstance;
    }

    /**
     * @return applicationContext
     */
    public static Context getAppContext() {
        return getInstance().getApplicationContext();
    }
}
