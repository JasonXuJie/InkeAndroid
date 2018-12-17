package com.jason.module_movie.mvp.contract;

import com.jason.module_movie.mvp.model.bean.MovieBean;
import com.jason.tools.base.IBaseView;

import java.util.List;

/**
 * Created by jason on 2018/12/13.
 */

public class IndexContract {

    public interface IndexViewImpl extends IBaseView{
        void getBanner(List<MovieBean.Movie> banner);
        void getHotMovies(List<MovieBean.Movie> movies);
        void getMoreMovies(List<MovieBean.Movie> movies);
    }


    public interface IndexPresenterImpl{
        void requestBanner(String cityName);
        void requestHotMovies(String cityName);
        void requestMoreMovies(String cityName);
    }
}
