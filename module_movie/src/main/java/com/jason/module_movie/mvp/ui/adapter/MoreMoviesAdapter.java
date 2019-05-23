package com.jason.module_movie.mvp.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.jason.module_movie.R;
import com.jason.module_movie.mvp.model.bean.MovieBean;
import com.jason.module_movie.mvp.ui.callback.OnItemClickListener;
import com.jason.tools.netimage.ImageLoader;
import com.jason.tools.utils.StringUtil;
import com.orhanobut.logger.Logger;

import java.util.List;

/**
 * Created by jason on 2018/12/14.
 */

public class MoreMoviesAdapter extends DelegateAdapter.Adapter<MoreMoviesAdapter.MoreMoviesViewHolder> {

    private Context mContext;
    private LayoutHelper mHelper;
    private List<MovieBean.Movie> data;
    private OnItemClickListener<MovieBean.Movie> listener;

    public MoreMoviesAdapter(Context context, LayoutHelper layoutHelper, List<MovieBean.Movie> data) {
        this.mContext = context;
        this.mHelper = layoutHelper;
        this.data = data;
    }



    public void setOnItemClickListener(OnItemClickListener<MovieBean.Movie> listener){
        this.listener = listener;
    }


    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mHelper;
    }

    @NonNull
    @Override
    public MoreMoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MoreMoviesViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_more_movie, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MoreMoviesViewHolder holder, @SuppressLint("RecyclerView") final int position) {
//        if (position % 2 == 0) {
//            layoutParams.mAspectRatio = 1.0f;
//        } else {
//            layoutParams.mAspectRatio = 0.8f;
//        }
        if (position%2!=0){
            VirtualLayoutManager.LayoutParams layoutParams = new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.mAspectRatio = 0.7f;
            LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,700);
            layoutParams1.setMargins(0,0,0,0);
            holder.img_movie_icon.setLayoutParams(layoutParams1);
            holder.itemView.setLayoutParams(layoutParams);
        }
        ImageLoader.loadImage(mContext, data.get(position).getImages().getMedium(), holder.img_movie_icon);
        holder.tv_movie_title.setText(data.get(position).getTitle());
        holder.tv_movie_rate.setText(StringUtil.builder("豆瓣评分:",String.valueOf(data.get(position).getRating().getAverage())));
        holder.tv_movie_plot.setText(StringUtil.builder("剧情:",data.get(position).getGenres().get(0)));
        holder.layout_item_more_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null)listener.onClick(holder.img_movie_icon,position,data.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class MoreMoviesViewHolder extends RecyclerView.ViewHolder {

        private CardView layout_item_more_movie;
        private ImageView img_movie_icon;
        private TextView tv_movie_title;
        private TextView tv_movie_rate;
        private TextView tv_movie_plot;

        public MoreMoviesViewHolder(View itemView) {
            super(itemView);
            img_movie_icon = itemView.findViewById(R.id.img_movie_icon);
            tv_movie_title = itemView.findViewById(R.id.tv_movie_title);
            tv_movie_rate = itemView.findViewById(R.id.tv_movie_rate);
            tv_movie_plot = itemView.findViewById(R.id.tv_movie_plot);
            layout_item_more_movie = itemView.findViewById(R.id.layout_item_more_movie);
        }
    }
}
