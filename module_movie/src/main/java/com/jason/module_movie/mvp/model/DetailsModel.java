package com.jason.module_movie.mvp.model;

import com.jason.module_movie.http.ApiService;
import com.jason.module_movie.http.HttpConfig;
import com.jason.module_movie.mvp.model.bean.DetailsBean;
import com.jason.module_movie.mvp.model.callback.OnGetDataCallBack;
import com.jason.tools.http.RetrofitManager;
import com.jason.tools.http.RxSchedulers;
import com.jason.tools.utils.StringUtil;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by jason on 2018/12/14.
 */

public class DetailsModel {

    public void requestDetails(String id, final OnGetDataCallBack<DetailsBean> callBack){
        RetrofitManager.getInstance()
                .getRetrofit()
                .create(ApiService.class)
                .getDetails(StringUtil.builder(HttpConfig.DOUBAN_DETAILS_URL,id))
                .compose(RxSchedulers.<DetailsBean>compose())
                .subscribe(new Observer<DetailsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DetailsBean detailsBean) {
                        if (callBack!=null)callBack.getData(detailsBean);
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
