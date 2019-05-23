package com.jason.plugin;

import com.jason.tools.base.BaseActivity;
import com.jason.tools.base.BasePresenter;

import androidx.navigation.Navigation;

/**
 * Navigation的使用
 * */
public class NavActivity extends BaseActivity {


    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_nav;
    }

    @Override
    public void initViews() {

    }

    @Override
    public void requestData() {

    }

    @Override
    public boolean onSupportNavigateUp() {
        return Navigation.findNavController(this,R.id.fragment).navigateUp();
    }
}
