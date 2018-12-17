package com.jason.module_event.mvp.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jason.module_event.R;

import java.util.List;

/**
 * Created by jason on 2018/11/2.
 */

public class EventsAdapter extends BaseQuickAdapter<String,BaseViewHolder> {



    public EventsAdapter(@Nullable List<String> data) {
        super(R.layout.item_events, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_title,item);
    }
}
