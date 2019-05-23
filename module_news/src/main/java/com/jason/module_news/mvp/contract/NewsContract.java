package com.jason.module_news.mvp.contract;

import com.jason.module_news.mvp.model.bean.NewsType;
import com.jason.tools.base.IBaseView;
import java.util.List;

/**
 * Created by jason on 2018/11/26.
 */

public class NewsContract {

    public interface INewsView extends IBaseView{
        void getNews(List<NewsType> types);
        void getDataFailed(String msg);
    }

    public interface INewsPresenter{
        void requestNews(String type);
    }
}
