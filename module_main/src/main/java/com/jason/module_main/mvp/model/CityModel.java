package com.jason.module_main.mvp.model;

import com.jason.module_main.http.ApiService;
import com.jason.module_main.http.HttpConfig;
import com.jason.module_main.mvp.model.bean.CityBean;
import com.jason.module_main.mvp.model.callback.OnGetDataCallBack;
import com.jason.tools.http.RetrofitManager;
import com.jason.tools.http.RxSchedulers;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by jason on 2018/12/18.
 */

public class CityModel {

    public void requestCitys(final OnGetDataCallBack<List<CityBean.City>> callBack){
        RetrofitManager.getInstance()
                .getRetrofit()
                .create(ApiService.class)
                .getCitys(HttpConfig.DOUBAN_CITY_URL)
                .compose(RxSchedulers.<CityBean>compose())
                .subscribe(new Observer<CityBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CityBean cityBean) {
                        if (callBack!=null) callBack.getData(cityBean.getLocs());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
