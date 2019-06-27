package com.jason.module_my.model.repository

import com.jason.module_my.http.ApiService
import com.jason.module_my.model.bean.DetailsResult
import com.jason.module_my.model.callback.OnRequestCallBack
import com.jason.tools.http.BaseObserver
import com.jason.tools.http.RetrofitManager
import com.jason.tools.http.RxSchedulers
import com.jason.tools.http.bean.JuheBaseResult

class DetailsRepository {

    fun requestDetails(id: String, callBack: OnRequestCallBack<List<DetailsResult>>?) {
        RetrofitManager.getInstance()
                .retrofit
                .create(ApiService::class.java)
                .getDetails(ApiService.HISTORY_KEY, id)
                .compose(RxSchedulers.compose<JuheBaseResult<List<DetailsResult>>>())
                .subscribe(object : BaseObserver<List<DetailsResult>>() {
                    override fun onSuccess(data: List<DetailsResult>) {
                        callBack?.getDataSuccess(data)
                    }

                    override fun onFailed(msg: String) {
                        callBack?.getDataFailed(msg)
                    }
                })

    }
}