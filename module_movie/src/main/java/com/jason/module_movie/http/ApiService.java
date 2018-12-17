package com.jason.module_movie.http;

import com.jason.module_movie.mvp.model.bean.DetailsBean;
import com.jason.module_movie.mvp.model.bean.MovieBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by jason on 2018/12/13.
 */

public interface ApiService {

    @GET
    Observable<MovieBean>  getBanner(@Url String url, @Query("city") String city, @Query("start") String start, @Query("count") String count);

    @GET
    Observable<DetailsBean> getDetails(@Url String url);

}
