package com.jason.module_my.mvp.ui.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jason.module_my.R;
import com.jason.module_my.model.bean.HistoryContent;
import com.jason.module_my.mvp.ui.adapter.HistoryAdapter;
import com.jason.module_my.viewmodel.HistoryListViewModel;
import com.jason.tools.base.BaseActivity;
import com.jason.tools.base.BasePresenter;
import com.jason.tools.config.RouterConfig;
import com.orhanobut.logger.Logger;

import java.util.List;

/**
 * Created by jason on 2018/11/27.
 */
@Route(path = RouterConfig.TODAY_PATH)
public class TodayInHistoryActivity extends BaseActivity {

    private AppBarLayout abl_history;
    private ImageView img_back;
    private TextView tv_title;
    private HistoryListViewModel viewModel;
    private RecyclerView rv_history;
    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_today_in_history;
    }

    @Override
    public void initViews() {
        img_back = findViewById(R.id.img_back);
        tv_title = findViewById(R.id.tv_title);
        rv_history = findViewById(R.id.rv_history);
        abl_history = findViewById(R.id.abl_history);
        rv_history.setLayoutManager(new LinearLayoutManager(this));
        rv_history.setHasFixedSize(true);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        abl_history.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    tv_title.setVisibility(View.VISIBLE);
                } else {
                    if (tv_title.getVisibility()==View.VISIBLE)  tv_title.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void requestData() {
        viewModel = ViewModelProviders.of(this).get(HistoryListViewModel.class);
        viewModel.getList().observe(this, new Observer<List<HistoryContent>>() {
            @Override
            public void onChanged(@Nullable final List<HistoryContent> data) {
                if (data==null){
                    showLoading();
                }else {
                    hideLoading();
                    HistoryAdapter adapter = new HistoryAdapter(data);
                    rv_history.setAdapter(adapter);
                    adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            Bundle bundle = new Bundle();
                            bundle.putString("title",data.get(position).getTitle());
                            bundle.putString("id",data.get(position).getE_id());
                            openActivityByParams(HistoryDetailsActivity.class,bundle);
                        }
                    });
                }
            }
        });
        viewModel.getErrMsg().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //Toast.makeText(TodayInHistoryActivity.this,s,Toast.LENGTH_SHORT).show();
                hideLoading();
            }
        });
    }
}
