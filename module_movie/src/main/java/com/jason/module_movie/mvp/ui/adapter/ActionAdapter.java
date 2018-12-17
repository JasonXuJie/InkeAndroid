package com.jason.module_movie.mvp.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.jason.module_movie.R;

/**
 * Created by jason on 2018/12/13.
 */

public class ActionAdapter extends DelegateAdapter.Adapter<ActionAdapter.ActionViewHolder> {


    private Context mContext;
    private LayoutHelper mHelper;

    public ActionAdapter(Context context,LayoutHelper layoutHelper){
        this.mContext = context;
        this.mHelper = layoutHelper;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mHelper;
    }

    @NonNull
    @Override
    public ActionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ActionViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_action,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ActionViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    static class ActionViewHolder extends RecyclerView.ViewHolder{

        private ImageView img_action;

        public ActionViewHolder(View itemView) {
            super(itemView);
            img_action = itemView.findViewById(R.id.img_action);
        }
    }
}
