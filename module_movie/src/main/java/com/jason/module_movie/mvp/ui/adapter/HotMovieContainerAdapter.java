package com.jason.module_movie.mvp.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jason.module_movie.R;
import com.jason.module_movie.mvp.model.bean.MovieBean;
import com.jason.module_movie.mvp.ui.callback.OnItemClickListener;

import java.util.List;

/**
 * Created by jason on 2018/12/14.
 */

public class HotMovieContainerAdapter extends DelegateAdapter.Adapter<HotMovieContainerAdapter.HotMovieViewHolder> {

    private Context mContext;
    private LayoutHelper mHelper;
    private List<MovieBean.Movie> data;
    private OnItemClickListener<MovieBean.Movie> listener;

    public HotMovieContainerAdapter(Context context, LayoutHelper helper, List<MovieBean.Movie> data){
        this.mContext = context;
        this.mHelper = helper;
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
    public HotMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HotMovieViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_hot_movie_container,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull HotMovieViewHolder holder, int position) {
        holder.item_tv_title.setText("热门电影");
        HotMovieAdapter adapter = new HotMovieAdapter(data);
        holder.rv_hot_movies.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (listener!=null) listener.onClick(view.findViewById(R.id.img_movie_banner),position,data.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    static class HotMovieViewHolder extends RecyclerView.ViewHolder{
        private TextView item_tv_title;
        private TextView item_tv_more;
        private RecyclerView rv_hot_movies;
        public HotMovieViewHolder(View itemView) {
            super(itemView);
            item_tv_title = itemView.findViewById(R.id.item_tv_title);
            item_tv_more = itemView.findViewById(R.id.item_tv_more);
            rv_hot_movies = itemView.findViewById(R.id.rv_hot_movies);
            rv_hot_movies.setLayoutManager(new LinearLayoutManager(itemView.getContext(),LinearLayoutManager.HORIZONTAL,false));
            rv_hot_movies.setHasFixedSize(true);
        }
    }
}
