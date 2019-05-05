package com.jason.module_main.mvp.ui.activity;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.android.flexbox.FlexboxLayout;
import com.jason.module_main.R;
import com.jason.tools.base.BaseActivity;
import com.jason.tools.base.BasePresenter;
import com.jason.tools.config.RouterConfig;

/**
 * Created by jason on 2018/12/20.
 */
@Route(path = RouterConfig.SEARCH_PATH)
public class SearchActivity extends BaseActivity {


    private Toolbar toolBar;
    private FlexboxLayout layout_flex;
    private String[] tags={"Android","Java","Kotlin","Ios","ObjectiveC","Swift","Flutter","Dart","ReactNative","JavaScript","Html5","CSS","Vue"};


    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    public void initViews() {
        toolBar = findViewById(R.id.toolBar);
        layout_flex = findViewById(R.id.layout_flex);
        setToolBarByBack(toolBar,"To be perfect",null);
        _renderTags();
    }

    @Override
    public void requestData() {

    }



    private void _renderTags(){
        for (String tag :tags){
            View tagView = View.inflate(this,R.layout.item_tag,null);
            TextView item_tv_tag_hot_search = tagView.findViewById(R.id.item_tv_tag_hot_search);
            item_tv_tag_hot_search.setText(tag);
            layout_flex.addView(tagView);
        }
    }
}
