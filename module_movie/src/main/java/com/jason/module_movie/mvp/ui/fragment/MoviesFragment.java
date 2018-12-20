package com.jason.module_movie.mvp.ui.fragment;


import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.FloatLayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.alibaba.android.vlayout.layout.StaggeredGridLayoutHelper;
import com.jason.module_movie.R;
import com.jason.module_movie.mvp.contract.IndexContract;
import com.jason.module_movie.mvp.model.bean.MovieBean;
import com.jason.module_movie.mvp.presenter.IndexPresenter;
import com.jason.module_movie.mvp.ui.activity.MovieDetailsActivity;
import com.jason.module_movie.mvp.ui.adapter.ActionAdapter;
import com.jason.module_movie.mvp.ui.adapter.BannerAdapter;
import com.jason.module_movie.mvp.ui.adapter.FloatAdapter;
import com.jason.module_movie.mvp.ui.adapter.FunctionAdapter;
import com.jason.module_movie.mvp.ui.adapter.GridAdapter;
import com.jason.module_movie.mvp.ui.adapter.HotMovieContainerAdapter;
import com.jason.module_movie.mvp.ui.adapter.MoreMoviesAdapter;
import com.jason.module_movie.mvp.ui.adapter.MyAdapter;
import com.jason.module_movie.mvp.ui.callback.OnItemClickListener;
import com.jason.tools.base.BaseFragment;
import com.jason.tools.config.RouterConfig;
import com.orhanobut.logger.Logger;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jason on 2018/11/14.
 */
@Route(path = RouterConfig.FRAGMENT_MOVIE_PATH)
public class MoviesFragment extends BaseFragment<IndexPresenter> implements IndexContract.IndexViewImpl {

    private RecyclerView rv_movies;
    private DelegateAdapter adapters;

    @Override
    public IndexPresenter createPresenter() {
        return new IndexPresenter();

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_movies;
    }

    @Override
    public void initViews(View view) {
        rv_movies = view.findViewById(R.id.rv_movies);
        VirtualLayoutManager layoutManager = new VirtualLayoutManager(activity);
        rv_movies.setLayoutManager(layoutManager);
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        rv_movies.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 10);
        adapters = new DelegateAdapter(layoutManager, false);
        rv_movies.setAdapter(adapters);
        //adapters.addAdapter(new MyAdapter(activity,new LinearLayoutHelper(15),data));
        //adapters.addAdapter(new GridAdapter(activity,new GridLayoutHelper(3,data.size(),10,15),data));
    }


    @Override
    public void requestData() {
        presenter.requestBanner("上海");
        presenter.requestHotMovies("上海");

    }

    @Override
    public void getBanner(final List<MovieBean.Movie> banner) {
        BannerAdapter adapter = new BannerAdapter(activity, new SingleLayoutHelper(), banner);
        adapters.addAdapter(adapter);
        adapter.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Bundle bundle = new Bundle();
                bundle.putString("id", banner.get(position).getId());
                openActivityByParams(MovieDetailsActivity.class, bundle);
            }
        });
        adapter.setOnCityClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(RouterConfig.CITY_PATH).navigation();
            }
        });
        adapter.setOnSearchListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               ARouter.getInstance().build(RouterConfig.SEARCH_PATH).navigation();
            }
        });
        adapters.addAdapter(new ActionAdapter(activity, new SingleLayoutHelper()));
    }

    @Override
    public void getHotMovies(List<MovieBean.Movie> movies) {
        HotMovieContainerAdapter adapter = new HotMovieContainerAdapter(activity, new SingleLayoutHelper(), movies);
        adapters.addAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener<MovieBean.Movie>() {
            @Override
            public void onClick(View v, int position, MovieBean.Movie data) {
                Bundle bundle = new Bundle();
                bundle.putString("id", data.getId());
                openActivityByParams(MovieDetailsActivity.class, bundle);
            }
        });
        SingleLayoutHelper layoutHelper = new SingleLayoutHelper();
        layoutHelper.setMargin(20, 15, 20, 15);
        adapters.addAdapter(new FunctionAdapter(activity, layoutHelper));
        presenter.requestMoreMovies("上海");
    }

    @Override
    public void getMoreMovies(List<MovieBean.Movie> movies) {
        StaggeredGridLayoutHelper layoutHelper = new StaggeredGridLayoutHelper(2, 20);
        layoutHelper.setMargin(20, 15, 20, 0);
        MoreMoviesAdapter adapter = new MoreMoviesAdapter(activity, layoutHelper, movies);
        adapters.addAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener<MovieBean.Movie>() {
            @Override
            public void onClick(View v, int position, MovieBean.Movie data) {
                Bundle bundle = new Bundle();
                bundle.putString("id", data.getId());
                openActivityByParams(MovieDetailsActivity.class, bundle);
            }
        });
        //adapters.addAdapter(new FloatAdapter(activity,new FloatLayoutHelper()));
    }


}
