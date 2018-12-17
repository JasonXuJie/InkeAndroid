package com.jason.tools.http.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by jason on 2018/11/26.
 */

public class NewsType implements MultiItemEntity {

    private int itemType;
    private NewsResult.News news;

    public NewsType(NewsResult.News news,int itemType){
        this.news = news;
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public NewsResult.News getNews() {
        return news;
    }

    public void setNews(NewsResult.News news) {
        this.news = news;
    }
}
