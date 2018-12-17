package com.jason.tools.netimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.concurrent.ExecutionException;

/**
 * Created by jason on 2018/10/23.
 */

public class ImageLoader {

    public interface OnLoadBitmap {
        void onReady(Bitmap resource);
        void onFailed();
    }

    public static void loadImage(Context context, String url,int resId,ImageView imageView){
        GlideApp.with(context).load(url).placeholder(resId).into(imageView);
    }

    public static void loadImage(Context context, String url,ImageView imageView){
        GlideApp.with(context).load(url).into(imageView);
    }

    public static void loadImage(Fragment fragment,String url,int resId,ImageView imageView){
        GlideApp.with(fragment).load(url).placeholder(resId).into(imageView);
    }


    public static Bitmap loadBitMap(Context context, String url, ImageView imageView) throws ExecutionException, InterruptedException {
        return GlideApp.with(context).asBitmap().load(url).submit(imageView.getWidth(),imageView.getHeight()).get();
    }


    public static void loadBitmap(Context context,String path, final OnLoadBitmap listener){
        GlideApp.with(context).asBitmap().load(path).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                listener.onReady(resource);
            }

            @Override
            public void onLoadFailed(@Nullable Drawable errorDrawable) {
                super.onLoadFailed(errorDrawable);
                listener.onFailed();
            }
        });
    }


    /**清理磁盘缓存需要在子线程执行*/
    public static void clearDiskCache(Context context){
        GlideApp.get(context).clearDiskCache();
    }


    /**清理内存缓存可以在UI线程中执行*/
    public static void clearMemoryCache(Context context){
        GlideApp.get(context).clearMemory();
    }

    public static void clearAll(Context context){
        clearDiskCache(context);
        clearMemoryCache(context);
    }






}
