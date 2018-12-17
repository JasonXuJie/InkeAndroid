package com.jason.tools.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.annotation.IntRange;

/**
 * Created by jason on 2018/11/7.
 */
//高斯模糊
public class GaussianBlurUtil {


    public Bitmap getBitMap(Context context, Bitmap bitmap, @IntRange(from = 1,to = 25)int radius){
        RenderScript renderScript = RenderScript.create(context);
        Allocation input = Allocation.createFromBitmap(renderScript,bitmap);
        Allocation output = Allocation.createTyped(renderScript,input.getType());
        ScriptIntrinsicBlur scriptIntrinsicBlur = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript));
        scriptIntrinsicBlur.setRadius(radius);
        scriptIntrinsicBlur.setInput(input);
        scriptIntrinsicBlur.forEach(output);
        output.copyTo(bitmap);
        return bitmap;
    }
}
