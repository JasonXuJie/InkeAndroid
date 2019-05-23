package com.jason.module_movie.mvp.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.app.hubert.guide.NewbieGuide;
import com.app.hubert.guide.model.GuidePage;
import com.jason.module_movie.R;
import com.jason.module_movie.mvp.model.bean.MovieBean;
import com.jason.module_movie.util.BannerLoader;
import com.orhanobut.logger.Logger;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jason on 2018/12/13.
 */

public class BannerAdapter extends DelegateAdapter.Adapter<BannerAdapter.BannerViewHolder> {

    private Context mContext;
    private LayoutHelper mHelper;
    private List<String> urls = new ArrayList<>();
    public OnBannerListener listener;
    private View.OnClickListener cityListener;
    private View.OnClickListener searchListener;
    private Fragment fragment;

    public BannerAdapter(Context context, LayoutHelper layoutHelper, List<MovieBean.Movie> banner, Fragment fragment){
        this.mContext = context;
        this.mHelper = layoutHelper;
        this.fragment = fragment;
        for (MovieBean.Movie movie:banner){
            urls.add(movie.getImages().getMedium());
        }
    }

    public void setOnBannerListener(OnBannerListener listener){
        this.listener = listener;
    }
    public void setOnCityClickListener(View.OnClickListener listener){
        this.cityListener = listener;
    }
    public void setOnSearchListener(View.OnClickListener listener){
        this.searchListener = listener;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mHelper;
    }

    @NonNull
    @Override
    public BannerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BannerViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_banner,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull BannerViewHolder holder, int position) {
        holder.bannerView.setImages(urls);
        holder.bannerView.start();
        if (listener!=null)holder.bannerView.setOnBannerListener(listener);
        if (cityListener!=null)holder.tv_city_name.setOnClickListener(cityListener);
        if (searchListener!=null)holder.img_search.setOnClickListener(searchListener);
        initGuide(holder.tv_city_name);

    }


    private void initGuide(TextView view){
        NewbieGuide.with(fragment)
                .setLabel("guide")
                .setShowCounts(1)
                .addGuidePage(GuidePage.newInstance()
                        .addHighLight(view)
                        .setLayoutRes(R.layout.layout_guide,R.id.btn_guide_ok)

                )
                .show();
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    static class BannerViewHolder extends RecyclerView.ViewHolder{

        private Banner bannerView;
        private TextView tv_city_name;
        private ImageView img_search;
        private ImageView img_scan;
        public BannerViewHolder(View itemView) {
            super(itemView);
            bannerView = itemView.findViewById(R.id.bannerView);
            tv_city_name = itemView.findViewById(R.id.tv_city_name);
            img_search = itemView.findViewById(R.id.img_search);
            img_scan = itemView.findViewById(R.id.img_scan);
            initBannerView();
        }


        private void initBannerView(){
            bannerView.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            bannerView.setImageLoader(new BannerLoader());
            bannerView.setBannerAnimation(Transformer.Default);
            bannerView.isAutoPlay(true);
            bannerView.setDelayTime(3000);
            bannerView.setIndicatorGravity(BannerConfig.RIGHT);
        }
    }
}
