package com.jason.module_event.mvp.contract;

import com.jason.module_event.mvp.model.bean.EventBean;
import com.jason.tools.base.IBaseView;

import java.util.List;

/**
 * Created by jason on 2018/12/18.
 */

public class EventsContract {

    public interface EventsViewImpl extends IBaseView{

        void getEvents(List<EventBean.EventsBean> list);
    }

    public interface EventsPresenterImpl{

        void requestEvents(String id,String type);
    }
}
