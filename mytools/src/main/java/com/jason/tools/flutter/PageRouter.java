package com.jason.tools.flutter;

import android.content.Context;
import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;
import com.jason.tools.config.RouterConfig;
import com.orhanobut.logger.Logger;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

public class PageRouter {

   // public static final String NATIVE_PAGE_URL = "sample://nativePage";
   // public static final String FLUTTER_PAGE_URL = "sample://flutterPage";
   // public static final String FLUTTER_FRAGMENT_PAGE_URL = "sample://flutterFragmentPage";

    public static final String FLUTTER_MOVIE_DETAILS_URL = "inke://movieDetails";
    public static final String NATIVE_WEB = "inke://nativeWeb";

    public static boolean openPageByUrl(Context context,String url){
        return openPageByUrl(context, url, 0);
    }


    public static boolean openPageByUrl(Context context,String url,int requestCode){
        try {
            if (url.startsWith(FLUTTER_MOVIE_DETAILS_URL)) {
                Map map = UrlUtil.paringParams(url);
                ARouter.getInstance().build(RouterConfig.DETAILS_PATH)
                        .withString("title", (String) map.get("title"))
                        .withString("id", (String) map.get("id"))
                        .withString("image", (String) map.get("image"))
                        .navigation();
                return true;
            }else if (url.startsWith(NATIVE_WEB)){
                Map map = UrlUtil.paringParams(url);
                String mobileUrl = URLDecoder.decode((String) map.get("url"),"UTF-8");
                ARouter.getInstance().build(RouterConfig.WEB_PATH).withString("url",mobileUrl).navigation();
                return true;
            }
        }catch (Throwable t){
            return false;
        }
        return false;
    }
}
