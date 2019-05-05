package com.jason.module_flutter.channel;

import android.app.Activity;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;

/**
 * Created by jason on 2018/10/9.
 */

public class FinishChannel implements MethodChannel.MethodCallHandler {

    public static String CHANNEL = "finishChannel";
    static MethodChannel channel;
    private Activity activity;

    private FinishChannel(Activity activity){
        this.activity = activity;
    }


    public static void registerWith(PluginRegistry.Registrar registrar){
        channel = new MethodChannel(registrar.messenger(),CHANNEL);
        FinishChannel aboutMeChannel = new FinishChannel(registrar.activity());
        channel.setMethodCallHandler(aboutMeChannel);
    }


    @Override
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
           if (methodCall.method.equals("finish")){
                activity.finish();
           }else {
               result.notImplemented();
           }
    }
}
