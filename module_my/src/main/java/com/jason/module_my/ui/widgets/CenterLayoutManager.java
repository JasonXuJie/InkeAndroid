package com.jason.module_my.ui.widgets;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by jason on 2018/11/7.
 */

public class CenterLayoutManager extends LinearLayoutManager {


    public CenterLayoutManager(Context context) {
        super(context);
    }

    public CenterLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public CenterLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
       CenterSmoothScroller smoothScroller = new CenterSmoothScroller(recyclerView.getContext());
        smoothScroller.setTargetPosition(position);
        startSmoothScroll(smoothScroller);
    }



    public int findCenterPosition(){
        return (findLastVisibleItemPosition()+findFirstVisibleItemPosition())/2;
    }








    private class CenterSmoothScroller extends LinearSmoothScroller{

        public CenterSmoothScroller(Context context) {
            super(context);
        }

        @Override
        public int calculateDtToFit(int viewStart, int viewEnd, int boxStart, int boxEnd, int snapPreference) {
            return boxStart+(boxEnd-boxStart)/2-(viewStart+(viewEnd-viewStart)/2);
        }
    }
}
