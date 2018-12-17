package com.jason.plugin;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jason.tools.base.BaseFragment;
import com.jason.tools.base.BasePresenter;

/**
 * Created by jason on 2018/11/23.
 */
public class TextFragment extends BaseFragment implements View.OnClickListener {


    private Button btn_test;

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_test;
    }

    @Override
    public void initViews(View view) {
        btn_test = view.findViewById(R.id.btn_test);
        btn_test.setOnClickListener(this);

    }

    @Override
    public void requestData() {

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_test) {
            Intent intent = new Intent(activity, TestActivity.class);
            startActivity(intent);
        }
    }
}
