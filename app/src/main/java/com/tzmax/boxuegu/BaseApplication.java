package com.tzmax.boxuegu;

import android.app.Application;
import android.content.Context;

public class BaseApplication extends Application {

    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

}
