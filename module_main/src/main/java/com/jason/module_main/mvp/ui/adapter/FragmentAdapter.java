package com.jason.module_main.mvp.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.jason.tools.base.BaseFragment;
import java.util.List;

/**
 * Created by jason on 2018/11/14.
 */

public class FragmentAdapter extends FragmentStatePagerAdapter {

    private List<BaseFragment> fragments;

    public FragmentAdapter(FragmentManager fm,List<BaseFragment> list) {
        super(fm);
        fragments = list;

    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
