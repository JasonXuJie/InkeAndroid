package com.jason.plugin;

import android.view.View;

import com.jason.tools.base.BaseFragment;
import com.jason.tools.base.BasePresenter;

import androidx.navigation.Navigation;

public class NavThreeFragment extends BaseFragment {

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_nav_three;
    }

    @Override
    public void initViews(View view) {
        view.findViewById(R.id.btn_nav_three).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.back_one);
            }
        });
    }

    @Override
    public void requestData() {

    }
}
