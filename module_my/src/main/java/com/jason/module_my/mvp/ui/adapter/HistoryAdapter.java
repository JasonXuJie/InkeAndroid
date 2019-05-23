package com.jason.module_my.mvp.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jason.module_my.R;
import com.jason.module_my.model.bean.HistoryContent;

import java.util.List;

/**
 * Created by jason on 2018/11/27.
 */

public class HistoryAdapter extends BaseQuickAdapter<HistoryContent, BaseViewHolder> {


    public HistoryAdapter(@Nullable List<HistoryContent> data) {
        super(R.layout.item_history, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HistoryContent item) {
        helper.setText(R.id.item_tv_title, item.getTitle())
                .setText(R.id.item_tv_content, item.getDate());

    }
}
