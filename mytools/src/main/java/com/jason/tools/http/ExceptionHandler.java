package com.jason.tools.http;

import android.net.ParseException;
import android.util.Log;

import com.google.gson.JsonParseException;
import com.jason.tools.utils.ToastUtil;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * 创建时间： 2018/1/4 0004.
 * 编写人：Tina
 * 邮箱：1208156801@qq.com
 * 功能描述：异常抛出帮助类
 */

public class ExceptionHandler {

    //这里也可以判断ApiException,返回服务器的数据
    public static void handle(Throwable e) {
        e.printStackTrace();
        if (e instanceof SocketTimeoutException) {
            //网络超时
            ToastUtil.showShort("网络连接异常");
        } else if (e instanceof ConnectException) {
            //均视为网络错误
            ToastUtil.showShort("网络连接异常");
        } else if (e instanceof JsonParseException || e instanceof JSONException || e instanceof ParseException) {
            //均视为解析错误
            ToastUtil.showShort("数据解析错误");
        } else {//未知错误
            try {
                Log.e("TAG", "错误: " + e.getMessage());
            } catch (Exception e1) {
                Log.e("TAG", "未知错误Debug调试 ");
            }

        }

    }

}
