package com.jason.module_movie.mvp.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.jason.module_movie.R;

/**
 * Created by jason on 2018/12/14.
 */

public class FunctionAdapter extends DelegateAdapter.Adapter<FunctionAdapter.FuncationViewHolder> {

    private Context mContext;
    private LayoutHelper mHelper;


    public FunctionAdapter(Context context, LayoutHelper layoutHelper){
        this.mContext = context;
        this.mHelper = layoutHelper;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mHelper;
    }

    @NonNull
    @Override
    public FuncationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FuncationViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_function,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull FuncationViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    static class FuncationViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout layout_prevue;
        private LinearLayout layout_comments;
        private LinearLayout layout_topic;
        private LinearLayout layout_sign;

        public FuncationViewHolder(View itemView) {
            super(itemView);
            layout_prevue = itemView.findViewById(R.id.layout_prevue);
            layout_comments = itemView.findViewById(R.id.layout_comments);
            layout_topic = itemView.findViewById(R.id.layout_topic);
            layout_sign = itemView.findViewById(R.id.layout_sign);
        }
    }
}
