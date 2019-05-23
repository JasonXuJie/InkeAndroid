package com.jason.module_my.model.repository;

import com.jason.module_my.config.Config;
import com.jason.module_my.http.ApiService;
import com.jason.module_my.model.bean.DetailsBean;
import com.jason.module_my.model.callback.OnRequestCallBack;
import com.jason.tools.http.BaseObserver;
import com.jason.tools.http.RetrofitManager;
import com.jason.tools.http.RxSchedulers;
import com.jason.tools.http.bean.JuheBaseResult;
import java.util.List;

/**
 * Created by jason on 2018/12/20.
 */

public class DetailsRepository {

    public void requestDetails(String id, final OnRequestCallBack<List<DetailsBean>> callBack){
        RetrofitManager.getInstance()
                .getRetrofit()
                .create(ApiService.class)
                .getDetails(Config.HISTORY_KEY,id)
                .compose(RxSchedulers.<JuheBaseResult<List<DetailsBean>>>compose())
                .subscribe(new BaseObserver<List<DetailsBean>>() {
                    @Override
                    public void onSuccess(List<DetailsBean> data) {
                        if (callBack!=null) callBack.getDataSuccess(data);
                    }

                    @Override
                    public void onFailed(String msg) {
                        if (callBack!=null) callBack.getDataFailed(msg);
                    }
                });
    }
}
