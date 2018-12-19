package com.jason.module_main.http;

import com.jason.module_main.mvp.model.bean.CityBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by jason on 2018/12/18.
 */

public interface ApiService {

    @GET
    Observable<CityBean> getCitys(@Url String url);
}
