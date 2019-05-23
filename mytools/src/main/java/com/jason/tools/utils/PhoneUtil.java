package com.jason.tools.utils;

import android.content.Context;
import android.telephony.TelephonyManager;

/**
 * 获取手机相关信息
 **/
public class PhoneUtil {

    /**
     * 获取IMEI
     **/
    public static String getIMEI(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String imei = telephonyManager.getDeviceId();
            if (imei == null) {
                imei = "";
            }
            return imei;
        } catch (SecurityException e) {
            e.printStackTrace();
            return "";
        }
    }
}
