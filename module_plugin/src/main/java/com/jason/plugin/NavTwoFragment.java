package com.jason.plugin;

import android.view.View;

import com.jason.tools.base.BaseFragment;
import com.jason.tools.base.BasePresenter;

import androidx.navigation.Navigation;

public class NavTwoFragment extends BaseFragment {
    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_nav_two;
    }

    @Override
    public void initViews(View view) {
       view.findViewById(R.id.btn_nav_two_back).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               //Navigation.findNavController(v).navigate(R.id.action_page1);
               Navigation.findNavController(v).navigateUp();//同返回
           }
       });
       view.findViewById(R.id.btn_nav_two).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Navigation.findNavController(v).navigate(R.id.action_page3);
           }
       });
    }

    @Override
    public void requestData() {

    }
}
