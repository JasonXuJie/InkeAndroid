package com.jason.module_main.notice;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

import com.jason.tools.utils.PlatformUtil;

/**
 * Created by jason on 2018/11/7.
 */

public class KeepAliveNotification {

    private static KeepAliveNotification instance;
    private Notification notification;
    private final String CHANNEL_ID = "331";
    private final String CHANNEL_NAME = "keepAlive";
    public static final int NOTIFY_ID = 31;

    public static synchronized KeepAliveNotification getInstance(Context context) {
        if (instance == null) {
            instance = new KeepAliveNotification(context);
        }
        return instance;
    }


    private KeepAliveNotification(Context context) {
        init(context);
    }


    private void init(Context context) {
        if (PlatformUtil.isO()) {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context.getApplicationContext(), CHANNEL_ID);
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.setShowBadge(false);
            NotificationManager manager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
            manager.createNotificationChannel(channel);
            notification = builder.build();
        }

    }

    public Notification get() {
        return notification;
    }
}

