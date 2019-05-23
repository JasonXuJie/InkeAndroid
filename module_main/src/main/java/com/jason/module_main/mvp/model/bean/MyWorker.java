package com.jason.module_main.mvp.model.bean;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import com.orhanobut.logger.Logger;

import androidx.work.Worker;
import androidx.work.WorkerParameters;


/**
 * WorkManager所执行的任务，轮询时间必须大于15分钟
 * **/
public class MyWorker extends Worker {


    public MyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Logger.e("doWork");
        return Worker.Result.success();
    }

    //抛到主线程中执行
    private void runOnUIThread(Runnable runnable) {
        new Handler(Looper.getMainLooper()).post(runnable);
    }

}
