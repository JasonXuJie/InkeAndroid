package com.jason.app.app;

import android.app.Application;
import android.content.Context;
import com.alibaba.android.arouter.launcher.ARouter;
import com.didi.virtualapk.PluginManager;
import com.jason.app.BuildConfig;
import com.jason.tools.utils.CrashHandler;
import com.jason.tools.utils.SharedUtil;
import com.jason.tools.utils.ToastUtil;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * Created by jason on 2018/10/23.
 */

public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG){
            ARouter.openLog();
            ARouter.openDebug();
            Logger.addLogAdapter(new AndroidLogAdapter());
        }
        ARouter.init(this);
        SharedUtil.init(this);
        ToastUtil.init(this);
        CrashHandler.getInstance().init(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        PluginManager.getInstance(base).init();
    }
}
