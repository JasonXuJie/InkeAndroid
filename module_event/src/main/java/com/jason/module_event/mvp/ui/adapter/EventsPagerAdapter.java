package com.jason.module_event.mvp.ui.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jason.module_event.mvp.ui.fragment.EventFragment;


/**
 * Created by jason on 2018/12/12.
 */

public class EventsPagerAdapter extends FragmentPagerAdapter {

    private String[] titles={"所有","音乐","电影","戏剧","公益","讲座","展览","聚会","旅行","其他"};
    private String[] tags={"top","shehui","guonei","guoji","yule","tiyu","junshi","keji","caijing","shishang"};


    public EventsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return EventFragment.newInstance(tags[position]);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return super.getItemPosition(object);
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
