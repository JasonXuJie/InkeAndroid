package com.jason.module_my.http;

import com.jason.module_my.model.bean.ChinesePairingBean;
import com.jason.module_my.model.bean.DetailsBean;
import com.jason.module_my.model.bean.HistoryContent;
import com.jason.module_my.model.bean.PairingBean;
import com.jason.tools.http.bean.JuheBaseResult;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by jason on 2018/12/20.
 */

public interface ApiService {

    @GET("todayOnhistory/queryDetail.php")
    Observable<JuheBaseResult<List<DetailsBean>>> getDetails(@Query("key") String key, @Query("e_id") String id);
    @GET
    Observable<PairingBean> getPairingData(@Url String url, @Query("key") String key , @Query("xingzuo1") String xingzuo1, @Query("xingzuo2") String xingzuo2);
    @GET
    Observable<ChinesePairingBean> getChinesePairingData(@Url String url, @Query("key") String key, @Query("shengxiao1") String shengxiao1, @Query("shengxiao2") String shengxiao2);

    @GET("todayOnhistory/queryEvent.php")
    Observable<JuheBaseResult<List<HistoryContent>>> getHistoryList(@Query("key")String key, @Query("date")String date);
}
