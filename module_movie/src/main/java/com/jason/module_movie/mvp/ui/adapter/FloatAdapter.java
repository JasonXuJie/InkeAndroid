package com.jason.module_movie.mvp.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.jason.module_movie.R;

import java.util.List;

/**
 * Created by jason on 2018/12/13.
 */

public class FloatAdapter extends DelegateAdapter.Adapter<FloatAdapter.FloatHolder> {

    private Context mContext;
    private LayoutHelper mHelper;


    public FloatAdapter(Context context, LayoutHelper layoutHelper){
        this.mContext = context;
        this.mHelper = layoutHelper;

    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mHelper;
    }

    @NonNull
    @Override
    public FloatAdapter.FloatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FloatHolder(LayoutInflater.from(mContext).inflate(R.layout.item_float,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull FloatAdapter.FloatHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }


    static class FloatHolder extends RecyclerView.ViewHolder{

        private ImageView img_float;

        public FloatHolder(View itemView) {
            super(itemView);
            img_float = itemView.findViewById(R.id.img_float);
        }
    }
}
