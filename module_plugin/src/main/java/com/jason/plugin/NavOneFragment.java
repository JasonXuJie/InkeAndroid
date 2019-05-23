package com.jason.plugin;

import android.support.v7.util.DiffUtil;
import android.view.View;

import com.jason.tools.base.BaseFragment;
import com.jason.tools.base.BasePresenter;

import androidx.navigation.Navigation;

public class NavOneFragment extends BaseFragment {


    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_nav_one;
    }

    @Override
    public void initViews(View view) {
        view.findViewById(R.id.btn_nav_one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_page2);
            }
        });
        view.findViewById(R.id.btn_nav_act).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.to_act);
            }
        });

    }

    @Override
    public void requestData() {

    }
}
