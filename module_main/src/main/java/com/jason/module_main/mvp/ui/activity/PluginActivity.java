package com.jason.module_main.mvp.ui.activity;

import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.jason.module_main.R;
import com.jason.tools.base.BaseActivity;
import com.jason.tools.base.BaseFragment;
import com.jason.tools.base.BasePresenter;
import com.orhanobut.logger.Logger;

/**
 * Created by jason on 2018/11/23.
 */

public class PluginActivity extends BaseActivity {


    private FrameLayout layout_container;

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_plugin;
    }

    @Override
    public void initViews() {
        try {
            Class cls = Class.forName("com.jason.plugin.TextFragment");
            if (cls!=null){
                Logger.e("!=null");
                BaseFragment fragment = (BaseFragment) cls.newInstance();
                Logger.e((fragment==null)+"");
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.layout_container,fragment);
                transaction.commit();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void requestData() {

    }
}
