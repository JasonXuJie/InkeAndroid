package com.jason.tools.http;


import com.jason.tools.http.bean.HistoryContent;
import com.jason.tools.http.bean.JuheBaseResult;
import com.jason.tools.http.bean.NewsResult;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by jason on 2018/11/26.
 */

public interface ApiService {
    @GET("toutiao/index")
    Observable<JuheBaseResult<NewsResult>> getNews(@Query("type") String type, @Query("key") String key);

    @GET("todayOnhistory/queryEvent.php")
    Observable<JuheBaseResult<List<HistoryContent>>> getHistoryList(@Query("key")String key, @Query("date")String date);

}
