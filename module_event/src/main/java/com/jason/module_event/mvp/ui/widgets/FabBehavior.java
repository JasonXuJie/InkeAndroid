package com.jason.module_event.mvp.ui.widgets;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by jason on 2018/11/2.
 */

public class FabBehavior extends FloatingActionButton.Behavior {

    private boolean visible = true;

    public FabBehavior(Context context, AttributeSet attrs){

    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull FloatingActionButton child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL || super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, axes, type);
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull FloatingActionButton child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);
        if (dyConsumed>0&&visible){
            visible = false;
            animOut(child);
        }else if(dyConsumed <0 && !visible){
            visible = true;
            animIn(child);
        }
    }


    private void animOut(FloatingActionButton fab){
       CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) fab.getLayoutParams();
       fab.animate().translationY(fab.getHeight() + layoutParams.bottomMargin).setInterpolator(new AccelerateInterpolator(3));
       ViewCompat.animate(fab).scaleX(0f).scaleY(0f).start();
    }


    private void animIn(FloatingActionButton fab){
       fab.animate().translationY(0).setInterpolator(new DecelerateInterpolator(3));
       ViewCompat.animate(fab).scaleX(1f).scaleY(1f).start();
    }
}
