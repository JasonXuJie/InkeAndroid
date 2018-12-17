package com.jason.module_movie.mvp.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
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

    public BannerAdapter(Context context, LayoutHelper layoutHelper, List<MovieBean.Movie> banner){
        this.mContext = context;
        this.mHelper = layoutHelper;
        for (MovieBean.Movie movie:banner){
            urls.add(movie.getImages().getMedium());
        }
    }

    public void setOnBannerListener(OnBannerListener listener){
        this.listener = listener;
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

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    static class BannerViewHolder extends RecyclerView.ViewHolder{

        private Banner bannerView;
        public BannerViewHolder(View itemView) {
            super(itemView);
            bannerView = itemView.findViewById(R.id.bannerView);
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
