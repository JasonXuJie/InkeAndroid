package com.jason.module_movie.mvp.model.callback;

import com.jason.module_movie.mvp.model.bean.MovieBean;

import java.util.List;

/**
 * Created by jason on 2018/12/13.
 */

public interface OnGetBannerCallBack {
    void getBanner(List<MovieBean.Movie> banner);
}
