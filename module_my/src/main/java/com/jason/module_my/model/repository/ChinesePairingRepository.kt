package com.jason.module_my.model.repository

import com.jason.module_my.config.Config
import com.jason.module_my.http.ApiService
import com.jason.module_my.model.bean.ChinesePairingBean
import com.jason.module_my.model.bean.PairingBean
import com.jason.module_my.model.callback.OnRequestCallBack
import com.jason.tools.http.RetrofitManager
import com.jason.tools.http.RxSchedulers

/**
 * Created by jason on 2018/12/26.
 */
class ChinesePairingRepository {

    public fun requestPairing(shengxiao1: String, shengxiao2: String, listener: OnRequestCallBack<ChinesePairingBean>) {
        RetrofitManager.getInstance()
                .retrofit
                .create(ApiService::class.java)
                .getChinesePairingData(Config.AFANDA_CHINESE_PAIRING_URL, Config.CHINESE_PAIR_KEY, shengxiao1, shengxiao2)
                .compose(RxSchedulers.compose())
                .subscribe { data -> run { listener.getDataSuccess(data) } }


    }
}