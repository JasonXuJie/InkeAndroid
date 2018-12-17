package com.jason.tools.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.util.Log;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * Created by jason on 2018/11/28.
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private static final String PATH = Environment.getExternalStorageDirectory().getPath() + "/crashlog/";
    private static final String FILE_NAME = "crash";
    private static final String FILE_SUFFIX = ".log";
    private static final String TAG = "CrashHandler";
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private Context mContext;
    private Thread.UncaughtExceptionHandler mDefaultCrashHandler;



    public static CrashHandler getInstance(){
        return CrashHandlerHolder.INSTANCE;
    }
    private static class CrashHandlerHolder{
        private static final CrashHandler INSTANCE = new CrashHandler();
    }

    private CrashHandler(){


    }

    public void init(Context context){
        mDefaultCrashHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        mContext = context.getApplicationContext();
    }



    @Override
    public void uncaughtException(Thread t, Throwable e) {
        //这里可以上传服务器或者写到sd卡中
        //打印堆栈
        dumpExceptionToSdCard(e);
        e.printStackTrace();
        if (mDefaultCrashHandler!=null){
            mDefaultCrashHandler.uncaughtException(t, e);
        }else {
            Process.killProcess(Process.myPid());
        }
    }

    private void dumpExceptionToSdCard(Throwable throwable){
        //判断SD卡不存在或无法使用
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            return;
        }
        File dir = new File(PATH);
        if (!dir.exists()){
            boolean isSuccess = dir.mkdir();
        }
        long current = System.currentTimeMillis();
        String time = simpleDateFormat.format(new Date(current));
        File file = new File(PATH + FILE_NAME + time + FILE_SUFFIX);
        Log.d(TAG, "File Path:" + file.getPath());
        try {
            PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            printWriter.println(time);
            dumpDeviceInfo(printWriter);
            throwable.printStackTrace();
            printWriter.close();
        }catch (Exception e){

        }


    }


    private void dumpDeviceInfo(PrintWriter printWriter)throws PackageManager.NameNotFoundException{
        PackageManager packageManager = mContext.getPackageManager();
        PackageInfo packageInfo = packageManager.getPackageInfo(mContext.getPackageName(),packageManager.GET_ACTIVITIES);
        printWriter.println("App Version"+packageInfo.versionName+"_"+packageInfo.versionCode);
        printWriter.println("Android OS Version"+ Build.VERSION.RELEASE+"_"+Build.VERSION.SDK_INT);
        printWriter.println("Vender:"+Build.MANUFACTURER);
        printWriter.println("Model:"+Build.MODEL);
        printWriter.println("CPU API"+Build.CPU_ABI);

    }
}
