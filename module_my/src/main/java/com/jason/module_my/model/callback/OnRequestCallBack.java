package com.jason.module_my.model.callback;

/**
 * Created by jason on 2018/11/27.
 */

public interface OnRequestCallBack<T> {

    void getDataSuccess(T data);

    void getDataFailed(String msg);

}
