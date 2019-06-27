package com.jason.module_my.http

import com.jason.module_my.model.bean.ChinesePairingBean
import com.jason.module_my.model.bean.DetailsResult
import com.jason.module_my.model.bean.HistoryContent
import com.jason.module_my.model.bean.PairingBean
import com.jason.tools.http.bean.JuheBaseResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {

    companion object{
        const val HISTORY_KEY = "077b7c987d84e8fee312c23685528f59"
        const val PAIRING_KEY = "13bcbc09790046f9b5d621ac7b70fc91"
        const val CHINESE_PAIR_KEY = "5734cd07cd7545b794a63dd7d8f49395"
        const val AFANDA_PAIRING_URL = "http://api.avatardata.cn/XingZuoPeiDui/Lookup"
        const val AFANDA_CHINESE_PAIRING_URL = "http://api.avatardata.cn/ShengXiaoPeiDui/Lookup"
    }

    @GET("todayOnhistory/queryDetail.php")
    fun getDetails(@Query("key")key:String,@Query("e_id")id:String):Observable<JuheBaseResult<List<DetailsResult>>>

    @GET
    fun getPairingData(@Url url:String,@Query("key")key:String,@Query("xingzuo1") xingzuo1:String,
                       @Query("xingzuo2")xingzuo2:String):Observable<JuheBaseResult<PairingBean>>

    @GET
    fun getChinesePairingData(@Url url:String,@Query("key")key:String,@Query("shengxiao1")shengxiao1:String,
                              @Query("shengxiao2")shengxiao2:String):Observable<JuheBaseResult<ChinesePairingBean>>

    @GET("todayOnhistory/queryEvent.php")
    fun getHistoryList(@Query("key")key:String,@Query("date")date:String):Observable<JuheBaseResult<List<HistoryContent>>>
}