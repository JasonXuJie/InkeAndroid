package com.jason.module_news.mvp.callback;

/**
 * Created by jason on 2018/11/26.
 */

public interface OnRequestCallBack<T> {
    void getDataSuccess(T data);
    void getDataFailed(String msg);
}
