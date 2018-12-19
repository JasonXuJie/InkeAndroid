package com.jason.module_event.mvp.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jason.module_event.R;
import com.jason.module_event.mvp.model.bean.EventBean;
import com.jason.tools.netimage.ImageLoader;
import com.jason.tools.utils.StringUtil;

import java.util.List;

/**
 * Created by jason on 2018/11/2.
 */

public class EventsAdapter extends BaseQuickAdapter<EventBean.EventsBean, BaseViewHolder> {


    public EventsAdapter(@Nullable List<EventBean.EventsBean> data) {
        super(R.layout.item_events, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, EventBean.EventsBean item) {
        ImageView item_img_event_icon = helper.getView(R.id.item_img_event_icon);
        String isHasTick = item.isHas_ticket()?"包含":"不包含";
        ImageLoader.loadImage(mContext, item.getImage(), item_img_event_icon);
        helper.setText(R.id.item_tv_event_title,item.getTitle())
                .setText(R.id.item_tv_event_place, StringUtil.builder("地点:",item.getLoc_name()))
                .setText(R.id.item_tv_event_address,StringUtil.builder("地址:",item.getAddress()))
                .setText(R.id.item_tv_event_price,item.getFee_str())
                .setText(R.id.item_tv_event_is_has_ticket,StringUtil.builder("是否含门票:",isHasTick))
                .setText(R.id.item_tv_event_start_time,StringUtil.builder("开始时间:",item.getBegin_time()))
                .setText(R.id.item_tv_event_end_time,StringUtil.builder("结束时间:",item.getEnd_time()))
                .setText(R.id.item_tv_event_participated,StringUtil.builder("已参与人数:",String.valueOf(item.getParticipant_count())))
                .setText(R.id.item_tv_event_want_participate,StringUtil.builder("想参加人数:",String.valueOf(item.getWisher_count())));
    }
}
