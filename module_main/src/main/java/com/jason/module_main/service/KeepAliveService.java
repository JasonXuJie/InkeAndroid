package com.jason.module_main.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import com.jason.module_main.notice.KeepAliveNotification;
import com.jason.tools.utils.PlatformUtil;

/**
 * Created by jason on 2018/11/7.
 */

public class KeepAliveService extends Service {


    public static void start(Context context){
        if (context!=null){
            Intent intent = new Intent(context,KeepAliveService.class);
            if (PlatformUtil.isO()){
                context.startForegroundService(intent);
            }else {
                context.startService(intent);
            }
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startForeground(KeepAliveNotification.NOTIFY_ID, KeepAliveNotification.getInstance(getApplicationContext()).get());
        return Service.START_STICKY;
    }


    @Override
    public void onDestroy() {
        stopForeground(true);
        super.onDestroy();
    }
}
