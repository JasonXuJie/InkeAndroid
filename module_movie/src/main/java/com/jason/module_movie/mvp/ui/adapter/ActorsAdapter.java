package com.jason.module_movie.mvp.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jason.module_movie.R;
import com.jason.module_movie.mvp.model.bean.Actor;
import com.jason.tools.netimage.ImageLoader;

import java.util.List;

/**
 * Created by jason on 2018/12/14.
 */

public class ActorsAdapter extends BaseQuickAdapter<Actor,BaseViewHolder> {


    public ActorsAdapter(@Nullable List<Actor> data) {
        super(R.layout.item_actor, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Actor item) {
        String tag = item.isDirector() ?"导演":"演员";
        helper.setText(R.id.tv_actor_name,item.getName())
                .setText(R.id.tv_is_director,tag);
        ImageView img_actor_icon = helper.getView(R.id.img_actor_icon);
        ImageLoader.loadImage(mContext,item.getImgUrl(),img_actor_icon);
    }
}
