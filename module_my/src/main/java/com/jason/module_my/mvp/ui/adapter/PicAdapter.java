package com.jason.module_my.mvp.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.jason.module_my.model.bean.DetailsBean;
import com.jason.tools.netimage.ImageLoader;
import java.util.List;

/**
 * Created by jason on 2018/12/20.
 */

public class PicAdapter extends PagerAdapter {

    private List<DetailsBean.PicUrlBean> urls;
    private OnPicItemListener listener;

    public PicAdapter(List<DetailsBean.PicUrlBean> urls){
        this.urls = urls;
    }


    @Override
    public int getCount() {
        return urls.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(container.getRootView());
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        ImageView imageView = new ImageView(container.getContext());
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        ImageLoader.loadImage(container.getContext(),urls.get(position).getUrl(),imageView);
        if (listener!=null) imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(urls.get(position).getUrl(),position);
            }
        });
        container.addView(imageView);
        return imageView;
    }


    public void setOnItemClickListener(OnPicItemListener listener){
        this.listener = listener;
    }

    public interface OnPicItemListener{
        void onClick(String url,int position);
    }
}
