package com.jason.plugin;

import android.support.v4.app.FragmentTransaction;
import com.jason.tools.base.BaseActivity;
import com.jason.tools.base.BasePresenter;
import com.orhanobut.logger.Logger;

public class FirstActivity extends BaseActivity{


    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_test_one;
    }

    @Override
    public void initViews() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.layout_fragment_container,new TextFragment());
        transaction.commit();
    }







    @Override
    public void requestData() {

    }


}
