package com.jason.tools.base;

import java.util.ArrayList;
import java.util.List;

/**
 * 单页面多网络请求
 */

public class MultiplePresenter<T extends IBaseView> extends BasePresenter<T> {
    private T mView;
    private List<BasePresenter> presenters = new ArrayList<>();
    @SafeVarargs
    public final <K extends BasePresenter<T>> void addPresenter(K...addPresenter){
        for (K presenter:addPresenter){
            presenter.attachView(mView);
            presenters.add(presenter);
        }
    }

    public MultiplePresenter(T mView){
        this.mView = mView;
    }

    @Override
    public void detachView() {
       for (BasePresenter presenter:presenters){
           presenter.detachView();
       }
    }
}
