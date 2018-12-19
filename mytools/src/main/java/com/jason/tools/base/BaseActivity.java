package com.jason.tools.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.jason.tools.R;
import com.jason.tools.utils.ActivityStackUtil;
import com.jason.tools.widgets.LoadingDialog;
import com.noober.background.BackgroundLibrary;

/**
 * Created by jason on 2018/11/14.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements IBaseView{


    protected T presenter;
    private LoadingDialog dialog;
    private TextView tv_title;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        BackgroundLibrary.inject(this);
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this).statusBarColor(R.color.C_0099fd).barAlpha(0.3f).fitsSystemWindows(true).init();
        initPresenter();
        setContentView(getLayoutId());
        initViews();
        requestData();
        ActivityStackUtil.getInstance().addActivity(this);
    }


    @Override
    public Context getCtx() {
        return this;
    }

    private void initPresenter(){
        presenter = createPresenter();
        if (presenter!=null){
            presenter.attachView(this);
        }
    }


    public abstract T  createPresenter();
    public abstract int getLayoutId();
    public abstract void initViews();
    public abstract void requestData();



    public void openActivityByParams(Class<?> cls, Bundle bundle){
        Intent intent = new Intent(this,cls);
        if (bundle!=null) intent.putExtras(bundle);
        startActivity(intent);
    }


    public void openActivityByNoParams(Class<?> cls){
        openActivityByParams(cls,null);
    }

    public void openActivityByPamrasForResult(Class<?> cls,Bundle bundle,int requestCode){
        Intent intent = new Intent(this,cls);
        if (bundle!=null)intent.putExtras(bundle);
        startActivityForResult(intent,requestCode);
    }

    public void openActivityByNoParamsForResult(Class<?> cls,int requsetCode){
        openActivityByPamrasForResult(cls,null,requsetCode);
    }


    public void showLoading(){
        if (dialog==null){
            dialog =  new LoadingDialog();
        }
        dialog.show(getSupportFragmentManager());
    }

    public void hideLoading(){
        if (dialog!=null){
            dialog.dismiss();
        }
    }




    public void setToolBarByBack(Toolbar toolbar, String title, View.OnClickListener listener){
        ImageView img_back = toolbar.findViewById(R.id.img_back);
        tv_title = toolbar.findViewById(R.id.tv_title);
        tv_title.setText(title);
        if (listener!=null){
            img_back.setOnClickListener(listener);
        }else {
            img_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

    public void setToolBarTitle(String title){
        if (tv_title!=null)tv_title.setText(title);
    }


    @Override
    protected void onDestroy() {
        if (presenter!=null)presenter.detachView();
        ActivityStackUtil.getInstance().removeActivity(this);
        ImmersionBar.with(this).destroy();
        super.onDestroy();

    }
}
