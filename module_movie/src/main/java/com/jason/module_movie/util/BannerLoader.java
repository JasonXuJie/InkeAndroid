package com.jason.module_movie.util;

import android.content.Context;
import android.widget.ImageView;

import com.youth.banner.loader.ImageLoader;

/**
 * Created by jason on 2018/12/13.
 */

public class BannerLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        com.jason.tools.netimage.ImageLoader.loadImage(context, (String) path,imageView);
    }
}
