package com.jason.module_my.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.jason.module_my.model.bean.PairingBean
import com.jason.module_my.model.callback.OnRequestCallBack
import com.jason.module_my.model.repository.PairingRepository

/**
 * Created by jason on 2018/12/26.
 */
class PairingViewModel(app: Application) : AndroidViewModel(app) {

    private var data: MutableLiveData<PairingBean>
    private var model: PairingRepository

    init {
        data = MutableLiveData()
        model = PairingRepository()
    }

    public fun getData(): MutableLiveData<PairingBean> = data

    public fun requestData(xingzuo1: String, xingzuo2: String) {
        data.value = null
        model.requestPairing(xingzuo1, xingzuo2, object : OnRequestCallBack<PairingBean> {

            override fun getDataFailed(msg: String?) {

            }

            override fun getDataSuccess(bean: PairingBean?) {
                data.value = bean
            }

        })
    }

    override fun onCleared() {
        super.onCleared()
    }
}