package com.jason.tools.utils;

import android.os.Build;

/**
 * Created by jason on 2018/11/7.
 */

public class PlatformUtil {


    public static boolean isO(){
        return Build.VERSION.SDK_INT>=Build.VERSION_CODES.O;
    }

    public static boolean isLollipop(){
        return Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP;
    }
}
