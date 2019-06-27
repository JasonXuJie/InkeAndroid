package com.jason.tools.flutter;

import com.orhanobut.logger.Logger;

import java.util.HashMap;
import java.util.Map;

public class UrlUtil {

    //获取对应url中的参数
    public static Map paringParams(String url) {
        Map<String, Object> map = new HashMap<>();
        url = url.replace("?", ";");
        if (!url.contains(";")) {
            return map;
        }
        if (url.split(";").length > 0) {
            String[] arr = url.split(";")[1].split("&");
            for (String s : arr) {
                Logger.e("循环");
                String key = s.split("=")[0];
                String value = s.split("=")[1];
                map.put(key, value);
            }
            return map;

        } else {
            return map;
        }
    }
}
