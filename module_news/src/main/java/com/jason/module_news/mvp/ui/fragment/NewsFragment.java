package com.jason.module_news.mvp.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jason.module_news.R;
import com.jason.module_news.mvp.contract.NewsContract;
import com.jason.module_news.mvp.model.bean.NewsType;
import com.jason.module_news.mvp.presenter.NewsPresenter;
import com.jason.module_news.mvp.ui.adapter.NewsAdapter;
import com.jason.tools.base.BaseLazyFragment;
import com.jason.tools.config.RouterConfig;
import com.orhanobut.logger.Logger;
import java.util.List;


/**
 * Created by jason on 2018/11/15.
 */

public class NewsFragment extends BaseLazyFragment<NewsPresenter> implements NewsContract.INewsView{


    private String tag;
    private RecyclerView rv_news;


    public static NewsFragment newInstance(String tag) {
        Bundle bundle = new Bundle();
        bundle.putString("tag", tag);
        NewsFragment fragment = new NewsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public NewsPresenter createPresenter() {
        return new NewsPresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    public void lazyInitViews(View view) {
        rv_news = view.findViewById(R.id.rv_news);
        rv_news.setLayoutManager(new LinearLayoutManager(activity));
        rv_news.setHasFixedSize(true);
        Bundle bundle = getArguments();
        if (bundle != null) {
            tag = bundle.getString("tag");
        }

    }

    @Override
    public void lazyRequestData() {
        presenter.requestNews(tag);
    }

    @Override
    public void onInVisible() {

    }

    @Override
    public void getNews(final List<NewsType> types) {
        NewsAdapter adapter = new NewsAdapter(types);
        rv_news.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
               ARouter.getInstance().build(RouterConfig.WEB_PATH).withString("url",types.get(position).getNews().getUrl()).navigation();
            }
        });
        //adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
    }

    @Override
    public void getDataFailed(String msg) {
        Logger.e(msg);
    }
}
