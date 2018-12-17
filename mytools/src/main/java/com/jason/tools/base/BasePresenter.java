package com.jason.tools.base;

import java.lang.ref.WeakReference;

/**
 * Created by jason on 2018/11/26.
 */

public abstract class BasePresenter<T extends IBaseView> implements IPresenter<T>{

    private WeakReference<T> viewRef;

    @Override
    public void attachView(T view) {
        viewRef = new WeakReference<>(view);
    }

    @Override
    public void detachView() {
        if (viewRef!=null){
            viewRef.clear();
            viewRef = null;
        }
    }

    public T getView(){
        if (viewRef!=null &&viewRef.get()!=null){
            return viewRef.get();
        }
        return null;
    }

    @Override
    public boolean isViewAttached() {
        return  viewRef != null && viewRef.get() != null;
    }
}
