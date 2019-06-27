package com.jason.module_movie.mvp.model;

import com.jason.module_movie.http.ApiService;
import com.jason.module_movie.http.HttpConfig;
import com.jason.module_movie.mvp.model.bean.MovieBean;
import com.jason.module_movie.mvp.model.callback.OnGetBannerCallBack;
import com.jason.tools.http.RetrofitManager;
import com.jason.tools.http.RxSchedulers;
import com.orhanobut.logger.Logger;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by jason on 2018/12/13.
 */

public class IndexModel {


    public void getBanner(String cityName, final OnGetBannerCallBack callBack){
        RetrofitManager.getInstance()
                .getRetrofit()
                .create(ApiService.class)
                .getBanner(HttpConfig.DOUBAN_BANNER_URL,cityName,"0","4")
                .compose(RxSchedulers.<MovieBean>compose())
                .subscribe(new Observer<MovieBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MovieBean movieBean) {
                        if (callBack!=null) callBack.getBanner(movieBean.getSubjects());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e("onError:"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }



    public void getHotMovies(String cityName, final OnGetBannerCallBack callBack){
        RetrofitManager.getInstance()
                .getRetrofit()
                .create(ApiService.class)
                .getBanner(HttpConfig.DOUBAN_BANNER_URL,cityName,"0","15")
                .compose(RxSchedulers.<MovieBean>compose())
                .subscribe(new Observer<MovieBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MovieBean movieBean) {
                        if (callBack!=null) callBack.getBanner(movieBean.getSubjects());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }



    public void getMoreMovies(String cityName, final OnGetBannerCallBack callBack){
        RetrofitManager.getInstance()
                .getRetrofit()
                .create(ApiService.class)
                .getBanner(HttpConfig.DOUBAN_BANNER_URL,cityName,"16","26")
                .compose(RxSchedulers.<MovieBean>compose())
                .subscribe(new Observer<MovieBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MovieBean movieBean) {
                        if (callBack!=null) callBack.getBanner(movieBean.getSubjects());
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
