package com.jason.module_movie.mvp.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jason.module_movie.R;
import com.jason.module_movie.mvp.contract.MovieDetailsContact;
import com.jason.module_movie.mvp.model.bean.Actor;
import com.jason.module_movie.mvp.model.bean.DetailsBean;
import com.jason.module_movie.mvp.presenter.MovieDetailsPresenter;
import com.jason.module_movie.mvp.ui.adapter.ActorsAdapter;
import com.jason.tools.base.BaseActivity;
import com.jason.tools.config.RouterConfig;
import com.jason.tools.netimage.ImageLoader;
import com.jason.tools.utils.StringUtil;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by jason on 2018/12/14.
 */

public class MovieDetailsActivity extends BaseActivity<MovieDetailsPresenter> implements MovieDetailsContact.MovieDetailsViewImpl, View.OnClickListener {


    private Toolbar toolBar;
    private ImageView img_movie_icon;
    private TextView tv_movie_title;
    private TextView tv_plot;
    private TextView tv_country;
    private RecyclerView rv_actors;
    private TextView tv_summary;
    private TextView tv_average;
    private TextView tv_reviews_count;
    private TextView tv_wish_count;
    private TextView tv_comments_count;
    private TextView tv_want_look;
    private TextView tv_looked;
    private DetailsBean mDetails;

    @Override
    public MovieDetailsPresenter createPresenter() {
        return new MovieDetailsPresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_movie_details;
    }

    @Override
    public void initViews() {
        toolBar = findViewById(R.id.toolBar);
        img_movie_icon = findViewById(R.id.img_movie_icon);
        tv_movie_title = findViewById(R.id.tv_movie_title);
        tv_plot = findViewById(R.id.tv_plot);
        tv_country = findViewById(R.id.tv_country);
        rv_actors = findViewById(R.id.rv_actors);
        rv_actors.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rv_actors.setHasFixedSize(true);
        tv_summary = findViewById(R.id.tv_summary);
        tv_average = findViewById(R.id.tv_average);
        tv_reviews_count = findViewById(R.id.tv_reviews_count);
        tv_wish_count = findViewById(R.id.tv_wish_count);
        tv_comments_count = findViewById(R.id.tv_comments_count);
        tv_want_look = findViewById(R.id.tv_want_look);
        tv_looked = findViewById(R.id.tv_looked);
        img_movie_icon.setOnClickListener(this);
        tv_want_look.setOnClickListener(this);
        tv_looked.setOnClickListener(this);
        setToolBarByBack(toolBar,"电影详情",null);
    }

    @Override
    public void requestData() {
        String id = getIntent().getStringExtra("id");
        presenter.requestDetails(id);
    }

    @Override
    public void getMovieDetails(DetailsBean details) {
        this.mDetails = details;
        tv_movie_title.setText(details.getTitle());
        StringBuilder plots = new StringBuilder();
        for (String plot : details.getGenres()) {
            plots.append(StringUtil.builder(plot,"\t\t"));
        }
        tv_plot.setText(plots);
        StringBuilder countries = new StringBuilder();
        for (String country : details.getCountries()) {
            countries.append(StringUtil.builder(country,"\t\t"));
        }
        tv_country.setText(countries);
        ImageLoader.loadImage(this, details.getImages().getMedium(), img_movie_icon);
        tv_summary.setText(details.getSummary());
        final List<Actor> actors = new ArrayList<>();
        for (DetailsBean.DirectorsBean director : details.getDirectors()) {
            Actor director1 = new Actor();
            director1.setName(director.getName());
            director1.setImgUrl(director.getAvatars().getMedium());
            director1.setDirector(true);
            director1.setUrl(director.getAlt());
            director1.setId(director.getId());
            actors.add(director1);
        }
        for (DetailsBean.CastsBean actor : details.getCasts()) {
            Actor actor1 = new Actor();
            actor1.setName(actor.getName());
            actor1.setId(actor.getId());
            actor1.setUrl(actor.getAlt());
            actor1.setDirector(false);
            actor1.setImgUrl(actor.getAvatars().getMedium());
            actors.add(actor1);
        }
        tv_average.setText(String.valueOf(details.getRating().getAverage()));
        tv_reviews_count.setText(String.valueOf(details.getRatings_count()));
        tv_wish_count.setText(String.valueOf(details.getWish_count()));
        tv_comments_count.setText(StringUtil.builder("评论人数:", String.valueOf(details.getComments_count())));
        ActorsAdapter actorsAdapter = new ActorsAdapter(actors);
        rv_actors.setAdapter(actorsAdapter);
        actorsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                   ARouter.getInstance().build(RouterConfig.WEB_PATH).withString("url",actors.get(position).getUrl()).navigation();
            }
        });
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.img_movie_icon) {
            ARouter.getInstance().build(RouterConfig.WEB_PATH).withString("url",mDetails.getShare_url()).navigation();
        } else if (viewId == R.id.tv_want_look) {

        } else if (viewId == R.id.tv_looked) {

        }
    }
}
