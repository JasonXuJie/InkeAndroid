package com.jason.module_news.mvp.model;

import com.jason.module_news.config.Config;
import com.jason.module_news.http.ApiService;
import com.jason.module_news.http.HttpConfig;
import com.jason.module_news.mvp.callback.OnRequestCallBack;
import com.jason.module_news.mvp.model.bean.NewsResult;
import com.jason.module_news.mvp.model.bean.NewsType;
import com.jason.tools.http.BaseObserver;
import com.jason.tools.http.RetrofitManager;
import com.jason.tools.http.RxSchedulers;
import com.jason.tools.http.bean.JuheBaseResult;
import com.jason.tools.utils.StringUtil;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jason on 2018/11/26.
 */

public class NewsModel {

    public void requestNews(String type, final OnRequestCallBack<List<NewsType>> callBack){
        RetrofitManager.getInstance()
                .getRetrofit()
                .create(ApiService.class)
                .getNews(HttpConfig.NEWS_URL,type, Config.NEWS_KEY)
                .compose(RxSchedulers.<JuheBaseResult<NewsResult>>compose())
                .subscribe(new BaseObserver<NewsResult>() {
                    @Override
                    public void onSuccess(NewsResult data) {
                        List<NewsType> types = new ArrayList<>();
                        for (NewsResult.News news:data.getData()){
                            if (!StringUtil.isEmpty(news.getThumbnail_pic_s02())&&!StringUtil.isEmpty(news.getThumbnail_pic_s03())){
                                NewsType multiType = new NewsType(news,1);
                                types.add(multiType);
                            }else {
                                NewsType singleType = new NewsType(news,0);
                                types.add(singleType);
                            }
                        }
                        if (callBack!=null) callBack.getDataSuccess(types);
                    }

                    @Override
                    public void onFailed(String msg) {
                        if (callBack!=null)callBack.getDataFailed(msg);
                    }
                });
    }
}
