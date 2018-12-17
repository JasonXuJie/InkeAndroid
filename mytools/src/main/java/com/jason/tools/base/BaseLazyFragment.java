package com.jason.tools.base;


import android.view.View;

/**
 * Created by jason on 2018/11/16.
 */

public abstract class BaseLazyFragment<T extends BasePresenter> extends BaseFragment<T> implements IBaseView{

    private boolean isLoaded = false;
    private boolean isCreated = false;
    private boolean isVisible = false;



    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()){
            isVisible = true;
            lazyLoad();
        }else {
            isVisible = false;
            onInVisible();
        }
    }



    @Override
    public final void initViews(View view) {
        lazyInitViews(view);
        isCreated = true;
        lazyLoad();

    }

    @Override
    public final void requestData() {

    }

    private void lazyLoad(){
        if (!isCreated||!isVisible||isLoaded){
            return;
        }
        lazyRequestData();
        isLoaded = true;
    }



    public abstract void lazyInitViews(View view);
    public abstract void lazyRequestData();
    public abstract void onInVisible();


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isLoaded = false;
        isCreated = false;
    }
}
