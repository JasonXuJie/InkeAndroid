package com.jason.module_movie.mvp.ui.callback;

import android.view.View;

/**
 * Created by jason on 2018/12/14.
 */

public interface OnItemClickListener<T> {
    void onClick(View v,int position,T data);
}
