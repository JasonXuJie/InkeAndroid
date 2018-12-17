package com.jason.tools.base;

/**
 * Created by jason on 2018/11/26.
 */

public interface IPresenter<T extends IBaseView> {

    /**
     * 绑定View
     * */
    void attachView(T view);


    /**
     * 解绑View
     * */
    void detachView();

    /**
     * 判断View是否已经销毁
     *
     * @return
     */
    boolean isViewAttached();
}
