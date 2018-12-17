package com.jason.module_movie.mvp.presenter;

import com.jason.module_movie.mvp.contract.IndexContract;
import com.jason.module_movie.mvp.model.IndexModel;
import com.jason.module_movie.mvp.model.bean.MovieBean;
import com.jason.module_movie.mvp.model.callback.OnGetBannerCallBack;
import com.jason.tools.base.BasePresenter;

import java.util.List;

/**
 * Created by jason on 2018/12/13.
 */

public class IndexPresenter extends BasePresenter<IndexContract.IndexViewImpl> implements IndexContract.IndexPresenterImpl {

    private IndexModel model;

    public IndexPresenter(){
        model = new IndexModel();
    }


    @Override
    public void requestBanner(String cityName) {
        model.getBanner(cityName, new OnGetBannerCallBack() {
            @Override
            public void getBanner(List<MovieBean.Movie> banner) {
                getView().getBanner(banner);
            }
        });
    }

    @Override
    public void requestHotMovies(String cityName) {
        model.getHotMovies(cityName, new OnGetBannerCallBack() {
            @Override
            public void getBanner(List<MovieBean.Movie> movies) {
                getView().getHotMovies(movies);
            }
        });
    }

    @Override
    public void requestMoreMovies(String cityName) {
        model.getMoreMovies(cityName, new OnGetBannerCallBack() {
            @Override
            public void getBanner(List<MovieBean.Movie> movies) {
                getView().getMoreMovies(movies);
            }
        });
    }


}
