package com.jason.module_main.mvp.ui.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by jason on 2018/10/26.
 */

public class GuideAdapter extends PagerAdapter {

    private int[] pics;

    public GuideAdapter(int[] pics) {
        this.pics = pics;
    }

    @Override
    public int getCount() {
        return pics.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(container.getRootView());
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView view = new ImageView(container.getContext());
        view.setImageResource(pics[position]);
        view.setScaleType(ImageView.ScaleType.FIT_XY);
        container.addView(view);
        return view;
    }
}
