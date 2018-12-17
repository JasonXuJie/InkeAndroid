package com.jason.plugin;

import android.app.Application;


import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * Created by jason on 2018/11/27.
 */

public class TestApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        Logger.addLogAdapter(new AndroidLogAdapter());
    }



}
