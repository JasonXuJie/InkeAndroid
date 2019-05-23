package com.jason.module_my.model.repository;

import com.jason.module_my.config.Config;
import com.jason.module_my.http.ApiService;
import com.jason.module_my.model.bean.HistoryContent;
import com.jason.module_my.model.callback.OnRequestCallBack;
import com.jason.tools.http.BaseObserver;
import com.jason.tools.http.RetrofitManager;
import com.jason.tools.http.RxSchedulers;
import com.jason.tools.http.bean.JuheBaseResult;

import java.util.Calendar;
import java.util.List;

/**
 * Created by jason on 2018/11/27.
 */

public class HistoryRepository {




    public void requestData(final OnRequestCallBack<List<HistoryContent>> callBack){
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String date = month+"/"+day;
        RetrofitManager.getInstance()
                .getRetrofit()
                .create(ApiService.class)
                .getHistoryList(Config.HISTORY_KEY,date)
                .compose(RxSchedulers.<JuheBaseResult<List<HistoryContent>>>compose())
                .subscribe(new BaseObserver<List<HistoryContent>>() {
                    @Override
                    public void onSuccess(List<HistoryContent> data) {
                        if (callBack!=null){
                            callBack.getDataSuccess(data);
                        }
                    }

                    @Override
                    public void onFailed(String msg) {
                        if (callBack!=null){
                            callBack.getDataFailed(msg);
                        }
                    }
                });
    }
}
