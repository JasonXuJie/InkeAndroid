package com.jason.module_event.http;


import com.jason.module_event.mvp.model.bean.EventBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by jason on 2018/12/18.
 */

public interface ApiService {

    @GET
    Observable<EventBean> getEvents(@Url String url, @Query("loc") String id, @Query("day_type") String dat_type, @Query("type") String type);

}
