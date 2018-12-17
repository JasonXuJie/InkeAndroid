package com.jason.module_my.mvp.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jason.module_my.R;
import com.jason.module_my.mvp.ui.adapter.TestAdapter;
import com.jason.module_my.mvp.ui.widgets.CenterLayoutManager;
import com.jason.module_my.mvp.ui.widgets.GalleryItemDecoration;
import com.jason.tools.base.BaseActivity;
import com.jason.tools.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jason on 2018/11/7.
 */
@Route(path = "/my/test")
public class TestActivity extends BaseActivity {


    private RecyclerView rv_hot_movies;
    List<String> data = new ArrayList<>();


    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    public void initViews() {
        rv_hot_movies = findViewById(R.id.rv_hot_movies);
        rv_hot_movies.setLayoutManager(new CenterLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        rv_hot_movies.setHasFixedSize(true);
        LinearSnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(rv_hot_movies);
    }

    @Override
    public void requestData() {
        for (int i=0;i<50;i++){
            data.add("item"+i);
        }
        TestAdapter adapter = new TestAdapter(data);
        rv_hot_movies.setAdapter(adapter);
        rv_hot_movies.addItemDecoration(new GalleryItemDecoration(10,20,50,200));
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                rv_hot_movies.smoothScrollToPosition(position);
            }
        });
        rv_hot_movies.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);


            }
        });
    }
}
