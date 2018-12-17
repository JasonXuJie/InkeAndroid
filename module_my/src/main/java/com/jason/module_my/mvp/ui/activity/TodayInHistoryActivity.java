package com.jason.module_my.mvp.ui.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.jason.module_my.R;
import com.jason.module_my.mvp.ui.adapter.HistoryAdapter;
import com.jason.module_my.viewmodel.HistoryListViewModel;
import com.jason.tools.base.BaseActivity;
import com.jason.tools.base.BasePresenter;
import com.jason.tools.http.bean.HistoryContent;
import java.util.List;

/**
 * Created by jason on 2018/11/27.
 */

public class TodayInHistoryActivity extends BaseActivity {


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
        rv_history = findViewById(R.id.rv_history);
        rv_history.setLayoutManager(new LinearLayoutManager(this));
        rv_history.setHasFixedSize(true);

    }

    @Override
    public void requestData() {
        viewModel = ViewModelProviders.of(this).get(HistoryListViewModel.class);
        viewModel.getList().observe(this, new Observer<List<HistoryContent>>() {
            @Override
            public void onChanged(@Nullable List<HistoryContent> data) {
                if (data==null){
                    showLoading();
                }else {
                    rv_history.setAdapter(new HistoryAdapter(data));
                    hideLoading();
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
