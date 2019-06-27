package com.jason.module_my.model.repository

import com.jason.module_my.http.ApiService
import com.jason.module_my.model.bean.ChinesePairingBean
import com.jason.module_my.model.callback.OnRequestCallBack
import com.jason.tools.http.RetrofitManager
import com.jason.tools.http.RxSchedulers
import com.jason.tools.http.bean.JuheBaseResult

/**
 * Created by jason on 2018/12/26.
 */
class ChinesePairingRepository {

    public fun requestPairing(shengxiao1: String, shengxiao2: String, listener: OnRequestCallBack<JuheBaseResult<ChinesePairingBean>>) {
        RetrofitManager.getInstance()
                .retrofit
                .create(ApiService::class.java)
                .getChinesePairingData(ApiService.AFANDA_CHINESE_PAIRING_URL, ApiService.CHINESE_PAIR_KEY, shengxiao1, shengxiao2)
                .compose(RxSchedulers.compose())
                .subscribe { data -> run { listener.getDataSuccess(data) } }


    }
}