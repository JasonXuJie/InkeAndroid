package com.jason.module_event.mvp.model;

import com.jason.module_event.http.ApiService;
import com.jason.module_event.http.HttpConfig;
import com.jason.module_event.mvp.model.bean.EventBean;
import com.jason.module_event.mvp.model.callback.OnGetDataCallBack;
import com.jason.tools.http.RetrofitManager;
import com.jason.tools.http.RxSchedulers;
import com.orhanobut.logger.Logger;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by jason on 2018/12/18.
 */

public class EventModel {

    public void requestEvent(String id, String type, final OnGetDataCallBack<List<EventBean.EventsBean>> callBack){
        RetrofitManager.getInstance()
                .getRetrofit()
                .create(ApiService.class)
                .getEvents(HttpConfig.DOUBAN_EVENTS_URL,id,"week",type)
                .compose(RxSchedulers.<EventBean>compose())
                .subscribe(new Observer<EventBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(EventBean eventBean) {
                       if (callBack!=null) callBack.getData(eventBean.getEvents());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
