package com.jason.module_news.http;



import com.jason.module_news.mvp.model.bean.NewsResult;
import com.jason.tools.http.bean.JuheBaseResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ApiService {

    @GET
    Observable<JuheBaseResult<NewsResult>> getNews(@Url String url, @Query("type") String type, @Query("key") String key);
}
