package com.jason.module_my.mvp.ui.widgets;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jason.tools.utils.DensityUtil;

/**
 * Created by jason on 2018/11/8.
 */

public class GalleryItemDecoration extends RecyclerView.ItemDecoration {

    private int mDefaultMargin;
    private int mTopMargin;
    private int mBottomMargin;
    private int mFirstAndLastMargin;

    public GalleryItemDecoration(int defaultMargin,int mTopMargin,int mBottomMargin,int mFirstAndLastMargin){
        this.mDefaultMargin = defaultMargin;
        this.mTopMargin = mTopMargin;
        this.mBottomMargin = mBottomMargin;
        this.mFirstAndLastMargin = mFirstAndLastMargin;
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildLayoutPosition(view);
        int itemCount = parent.getAdapter().getItemCount();
        int leftMargin;
        int rightMargin;
        if (position==0){
            leftMargin = DensityUtil.dip2px(parent.getContext(),mFirstAndLastMargin);
        }else {
            leftMargin = DensityUtil.dip2px(parent.getContext(),mDefaultMargin);
        }
        if (position==(itemCount-1)){
            rightMargin = DensityUtil.dip2px(parent.getContext(),mFirstAndLastMargin);
        }else {
            rightMargin = DensityUtil.dip2px(parent.getContext(),mDefaultMargin);
        }
        if (view.getLayoutParams() instanceof RecyclerView.LayoutParams){
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
            layoutParams.setMargins(leftMargin,mTopMargin,rightMargin,mBottomMargin);
            view.setLayoutParams(layoutParams);
        }
        super.getItemOffsets(outRect, view, parent, state);

    }
}
