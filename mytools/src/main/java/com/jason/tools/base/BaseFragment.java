package com.jason.tools.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jason.tools.widgets.LoadingDialog;

/**
 * Created by jason on 2018/11/14.
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements IBaseView{

    protected BaseActivity activity;
    private LoadingDialog loadingDialog;
    protected T presenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (BaseActivity) context;
    }


    @Nullable
    @Override
    public final View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initPresenter();
        View view = inflater.inflate(getLayoutId(),container,false);
        initViews(view);
        requestData();
        return view;
    }

    private void initPresenter(){
        presenter = createPresenter();
        if (presenter!=null){
            presenter.attachView(this);
        }
    }


    public abstract T createPresenter();

    public abstract int getLayoutId();

    public abstract void initViews(View view);

    public abstract void requestData();


    @Override
    public void onDestroyView() {
        if (presenter!=null) presenter.detachView();
        super.onDestroyView();
    }

    @Override
    public Context getCtx() {
        return activity;
    }

    @Override
    public void showLoading() {
        if (loadingDialog==null){
            loadingDialog = new LoadingDialog();
        }
        loadingDialog.show(activity.getSupportFragmentManager());
    }

    @Override
    public void hideLoading() {
        if (loadingDialog!=null)loadingDialog.dismiss();
    }

    public void openActivityByParams(Class<?> cls, Bundle bundle){
        Intent intent = new Intent(activity,cls);
        if (bundle!=null) intent.putExtras(bundle);
        this.startActivity(intent);
    }


    public void openActivityByNoParams(Class<?> cls){
        openActivityByParams(cls,null);
    }


    public void openActivityByParamsForResult(Class<?> cls,Bundle bundle,int requestCode){
        Intent intent = new Intent(activity,cls);
        if (bundle!=null)intent.putExtras(bundle);
        this.startActivityForResult(intent,requestCode);
    }


    public void openActivityByNoParamsForResult(Class<?> cls,int requestCode){
        openActivityByParamsForResult(cls,null,requestCode);
    }



}
