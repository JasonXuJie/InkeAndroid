package com.jason.module_event.mvp.presenter;

import com.jason.module_event.mvp.contract.EventsContract;
import com.jason.module_event.mvp.model.EventModel;
import com.jason.module_event.mvp.model.bean.EventBean;
import com.jason.module_event.mvp.model.callback.OnGetDataCallBack;
import com.jason.tools.base.BasePresenter;

import java.util.List;

/**
 * Created by jason on 2018/12/18.
 */

public class EventsPresenter extends BasePresenter<EventsContract.EventsViewImpl> implements EventsContract.EventsPresenterImpl {

    private EventModel model;

    public EventsPresenter(){
        model = new EventModel();
    }

    @Override
    public void requestEvents(String id,String type) {
        model.requestEvent(id, type, new OnGetDataCallBack<List<EventBean.EventsBean>>() {
            @Override
            public void getData(List<EventBean.EventsBean> data) {
                getView().getEvents(data);
            }
        });
    }
}
