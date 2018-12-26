package com.jason.module_my.model.repository

import com.jason.module_my.config.Config
import com.jason.module_my.http.ApiService
import com.jason.module_my.model.bean.PairingBean
import com.jason.module_my.model.callback.OnRequestCallBack
import com.jason.tools.http.RetrofitManager
import com.jason.tools.http.RxSchedulers

/**
 * Created by jason on 2018/12/26.
 */
class PairingRepository {


    public fun requestPairing(xingzuo1:String,xingzuo2:String,listener:OnRequestCallBack<PairingBean>){
        RetrofitManager.getInstance()
                .retrofit
                .create(ApiService::class.java)
                .getPairingData(Config.AFANDA_PAIRING_URL,Config.PAIRING_KEY,xingzuo1,xingzuo2)
                .compose(RxSchedulers.compose())
                .subscribe { data-> run { listener.getDataSuccess(data) } }


    }
}