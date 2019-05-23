package com.jason.module_news.mvp.presenter;

import com.jason.module_news.mvp.callback.OnRequestCallBack;
import com.jason.module_news.mvp.contract.NewsContract;
import com.jason.module_news.mvp.model.NewsModel;
import com.jason.module_news.mvp.model.bean.NewsType;
import com.jason.tools.base.BasePresenter;


import java.util.List;

/**
 * Created by jason on 2018/11/26.
 */

public class NewsPresenter extends BasePresenter<NewsContract.INewsView> implements NewsContract.INewsPresenter{

    private NewsModel model;

    public NewsPresenter(){
        model = new NewsModel();
    }

    @Override
    public void requestNews(String type) {
        getView().showLoading();
        model.requestNews(type, new OnRequestCallBack<List<NewsType>>() {
            @Override
            public void getDataSuccess(List<NewsType> data) {
                if (isViewAttached()){
                    getView().hideLoading();
                    getView().getNews(data);
                }
            }

            @Override
            public void getDataFailed(String msg) {
                if (isViewAttached()){
                    getView().hideLoading();
                    getView().getDataFailed(msg);
                }
            }
        });
    }
}
