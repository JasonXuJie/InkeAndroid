package com.jason.module_movie.mvp.contract;

import com.jason.module_movie.mvp.model.bean.DetailsBean;
import com.jason.tools.base.IBaseView;

/**
 * Created by jason on 2018/12/14.
 */

public class MovieDetailsContact {

    public interface MovieDetailsViewImpl extends IBaseView{
        void getMovieDetails(DetailsBean detailsBean);
    }


    public interface MovieDetailsPresenterImpl{
        void requestDetails(String id);
    }
}
