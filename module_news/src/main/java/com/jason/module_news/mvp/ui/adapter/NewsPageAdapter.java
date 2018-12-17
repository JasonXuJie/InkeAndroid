package com.jason.module_news.mvp.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.util.ArrayMap;
import android.util.SparseArray;

import com.jason.module_news.mvp.ui.fragment.NewsFragment;

/**
 * Created by jason on 2018/11/15.
 */

public class NewsPageAdapter extends FragmentPagerAdapter {


    private String[] titles={"头条","社会","国内","国际","娱乐","体育","军事","科技","财经","时尚"};
    private String[] tags={"top","shehui","guonei","guoji","yule","tiyu","junshi","keji","caijing","shishang"};


    public NewsPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return NewsFragment.newInstance(tags[position]);
    }

    @Override
    public int getCount() {
        return titles.length;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
