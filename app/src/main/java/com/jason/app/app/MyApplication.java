package com.jason.app.app;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.didi.virtualapk.PluginManager;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * Created by jason on 2018/10/23.
 */

public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(this);
        Logger.addLogAdapter(new AndroidLogAdapter());
        Logger.e("onCreate");
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        PluginManager.getInstance(base).init();
        Logger.e("attachBaseContext");
    }
}
