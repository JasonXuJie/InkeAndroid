package com.jason.module_movie.mvp.presenter;

import com.jason.module_movie.mvp.contract.MovieDetailsContact;
import com.jason.module_movie.mvp.model.DetailsModel;
import com.jason.module_movie.mvp.model.bean.DetailsBean;
import com.jason.module_movie.mvp.model.callback.OnGetDataCallBack;
import com.jason.tools.base.BasePresenter;

/**
 * Created by jason on 2018/12/14.
 */

public class MovieDetailsPresenter extends BasePresenter<MovieDetailsContact.MovieDetailsViewImpl> implements MovieDetailsContact.MovieDetailsPresenterImpl{


    private DetailsModel model;

    public MovieDetailsPresenter(){
        model = new DetailsModel();
    }


    @Override
    public void requestDetails(String id) {
        getView().showLoading();
        model.requestDetails(id, new OnGetDataCallBack<DetailsBean>() {
            @Override
            public void getData(DetailsBean data) {
                getView().getMovieDetails(data);
                getView().hideLoading();
            }
        });
    }
}
