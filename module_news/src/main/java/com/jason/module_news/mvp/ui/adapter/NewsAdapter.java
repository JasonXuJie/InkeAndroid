package com.jason.module_news.mvp.ui.adapter;

import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jason.module_news.R;
import com.jason.module_news.mvp.model.bean.NewsType;
import com.jason.tools.netimage.ImageLoader;
import com.jason.tools.utils.PlatformUtil;
import com.jason.tools.utils.StringUtil;

import java.util.List;

/**
 * Created by jason on 2018/11/21.
 */

public class NewsAdapter extends BaseMultiItemQuickAdapter<NewsType, BaseViewHolder> {

    public static final int TYPE_SINGLE = 0;
    public static final int TYPE_MULTI = 1;

    public NewsAdapter(@Nullable List<NewsType> types) {
        super(types);
        addItemType(TYPE_SINGLE, R.layout.item_news_single);
        addItemType(TYPE_MULTI, R.layout.item_news_multi);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsType type) {
        if (helper.getItemViewType() == TYPE_SINGLE) {
            RelativeLayout item_layout = helper.getView(R.id.item_layout);
            ImageView item_img_pic = helper.getView(R.id.item_img_pic);
            if (PlatformUtil.isLollipop()) {
                item_layout.setBackgroundResource(R.drawable.ripple_item_news);
            }
            helper.setText(R.id.item_tv_title, type.getNews().getTitle())
                    .setText(R.id.item_tv_from, StringUtil.builder("来源于:", type.getNews().getAuthor_name()))
                    .setText(R.id.item_tv_time, type.getNews().getDate());
            ImageLoader.loadImage(mContext, type.getNews().getThumbnail_pic_s(), R.drawable.no_banner, item_img_pic);
        } else if (helper.getItemViewType() == TYPE_MULTI) {
            ConstraintLayout item_layout =  helper.getView(R.id.item_layout);
            if (PlatformUtil.isLollipop()){
                item_layout.setBackgroundResource(R.drawable.ripple_item_news);
            }
            ImageView item_img_pic_one = helper.getView(R.id.item_img_pic_one);
            ImageView item_img_pic_two = helper.getView(R.id.item_img_pic_two);
            ImageView item_img_pic_three = helper.getView(R.id.item_img_pic_three);
            helper.setText(R.id.item_tv_title, type.getNews().getTitle())
                    .setText(R.id.item_tv_from, StringUtil.builder("来源于:", type.getNews().getAuthor_name()))
                    .setText(R.id.item_tv_time, type.getNews().getDate());
            ImageLoader.loadImage(mContext, type.getNews().getThumbnail_pic_s(), R.drawable.no_banner, item_img_pic_one);
            ImageLoader.loadImage(mContext, type.getNews().getThumbnail_pic_s02(), R.drawable.no_banner, item_img_pic_two);
            ImageLoader.loadImage(mContext, type.getNews().getThumbnail_pic_s03(), R.drawable.no_banner, item_img_pic_three);
        }

    }
}
