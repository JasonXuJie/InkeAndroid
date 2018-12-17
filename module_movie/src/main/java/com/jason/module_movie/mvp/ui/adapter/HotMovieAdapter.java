package com.jason.module_movie.mvp.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jason.module_movie.R;
import com.jason.module_movie.mvp.model.bean.MovieBean;
import com.jason.tools.netimage.ImageLoader;
import com.jason.tools.utils.StringUtil;

import java.util.List;

/**
 * Created by jason on 2018/12/14.
 */

public class HotMovieAdapter extends BaseQuickAdapter<MovieBean.Movie,BaseViewHolder> {


    public HotMovieAdapter(@Nullable List<MovieBean.Movie> data) {
        super(R.layout.item_hot_movie, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MovieBean.Movie item) {
        helper.setText(R.id.tv_movie_rate, StringUtil.builder("豆瓣评分:",String.valueOf(item.getRating().getAverage())))
        .setText(R.id.tv_movie_title,item.getTitle());
        ImageView imageView = helper.getView(R.id.img_movie_banner);
        ImageLoader.loadImage(mContext,item.getImages().getMedium(),imageView);
    }
}
