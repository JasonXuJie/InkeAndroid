package com.jason.tools.flutter;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.jason.tools.config.RouterConfig;
import com.orhanobut.logger.Logger;
import com.taobao.idlefish.flutterboost.FlutterBoostPlugin;
import com.taobao.idlefish.flutterboost.interfaces.IPlatform;
import java.util.Map;
import io.flutter.view.FlutterMain;


public class FlutterBoost {

    public static void register(final Application application){
        //此处必须启动初始化，主要是载入Flutter引擎文件
        FlutterMain.startInitialization(application);
        FlutterBoostPlugin.init(new IPlatform() {
            @Override
            public Application getApplication() {
                return application;
            }

            @Override
            public Activity getMainActivity() {
                Logger.e("getMainActivity");
//                Activity activity = (Activity) ARouter.getInstance().build(RouterConfig.MAIN_PATH).navigation();
//                if (activity !=null){
//                    Logger.e("!=null");
//                    return activity;
//                }
//                Logger.e("==null");
                return null;
            }

            @Override
            public boolean isDebug() {
                return true;
            }

            /**
             * 如果flutter想打开一个本地页面，将会回调这个方法，页面参数将会拼接在url中
             *
             * 例如：sample://nativePage?aaa=bbb
             *
             * 参数就是类似 aaa=bbb 这样的键值对
             *
             * @param context
             * @param url
             * @param requestCode
             * @return
             */
            @Override
            public boolean startActivity(Context context, String url, int requestCode) {
                return PageRouter.openPageByUrl(context,url,requestCode);
            }

            @Override
            public Map getSettings() {
                return null;
            }
        });
    }
}
