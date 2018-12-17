package debug;

import android.app.Application;
import android.content.Context;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * Created by jason on 2018/11/14.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //PluginManager.getInstance(base).init();
    }
}
