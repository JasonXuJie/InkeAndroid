package com.jason.module_my.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.jason.module_my.model.bean.ChinesePairingBean
import com.jason.module_my.model.bean.PairingBean
import com.jason.module_my.model.callback.OnRequestCallBack
import com.jason.module_my.model.repository.ChinesePairingRepository
import com.jason.module_my.model.repository.PairingRepository
import com.jason.tools.http.bean.HistoryContent

/**
 * Created by jason on 2018/12/26.
 */
class ChinesePairingViewModel(app: Application) : AndroidViewModel(app) {

    private var data: MutableLiveData<ChinesePairingBean>
    private var model: ChinesePairingRepository

    init {
        data = MutableLiveData()
        model = ChinesePairingRepository()
    }

    public fun getData(): MutableLiveData<ChinesePairingBean> = data

    public fun requestData(shengxiao1: String, shengxiao2: String) {
        data.value = null
        model.requestPairing(shengxiao1, shengxiao2, object : OnRequestCallBack<ChinesePairingBean> {

            override fun getDataFailed(msg: String?) {

            }

            override fun getDataSuccess(bean: ChinesePairingBean?) {
                data.value = bean
            }

        })
    }

    override fun onCleared() {
        super.onCleared()
    }
}