package com.jason.module_movie.mvp.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.jason.module_movie.R;

import java.util.List;

/**
 * Created by jason on 2018/12/13.
 */

public class MyAdapter extends DelegateAdapter.Adapter<MyAdapter.MyHolder> {

    private Context mContext;
    private LayoutHelper mHelper;
    private List<String> data;

    public MyAdapter(Context context,LayoutHelper layoutHelper,List<String> data){
        this.mContext = context;
        this.mHelper = layoutHelper;
        this.data = data;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mHelper;
    }

    @NonNull
    @Override
    public MyAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(mContext).inflate(R.layout.item_test,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyHolder holder, int position) {
        holder.tv_title.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    static class MyHolder extends RecyclerView.ViewHolder{

        private TextView tv_title;

        public MyHolder(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
        }
    }
}
