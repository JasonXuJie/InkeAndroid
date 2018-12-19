package com.jason.module_main.mvp.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jason.module_main.R;
import com.jason.module_main.mvp.model.bean.CityBean;

import java.util.List;

/**
 * Created by jason on 2018/12/18.
 */

public class CityAdapter extends BaseQuickAdapter<CityBean.City, BaseViewHolder> {


    public CityAdapter(@Nullable List<CityBean.City> data) {
        super(R.layout.item_city, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CityBean.City item) {
        helper.setText(R.id.item_btn_city_name,item.getName());
    }
}
