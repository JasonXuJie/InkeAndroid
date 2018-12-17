package com.jason.module_my.mvp.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jason.module_my.R;

import java.util.List;

/**
 * Created by jason on 2018/11/7.
 */

public class TestAdapter extends BaseQuickAdapter<String,BaseViewHolder> {


    public TestAdapter(@Nullable List<String> data) {
        super(R.layout.item_test, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.item_tv_test,item);
    }
}
