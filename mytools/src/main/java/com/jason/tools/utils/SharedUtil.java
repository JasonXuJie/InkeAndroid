package com.jason.tools.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

/**
 * Created by jason on 2018/3/6.
 */

public class SharedUtil {


    private static SharedUtil instance;
    private static Context mContext;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;


    public static SharedUtil getInstance(){
        if (instance == null) {
            synchronized (SharedUtil.class) {
                if (instance == null) {
                    instance = new SharedUtil();
                }
            }
        }
        return instance;
    }


    public static void init(Context context){
        mContext = context.getApplicationContext();
    }


    private SharedUtil(){
        if (mContext==null) throw new UnsupportedOperationException("should be init");
        sharedPreferences = mContext.getSharedPreferences(mContext.getPackageName(),Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }


    /**
     * 增
     *
     * @param key
     * @param object 数据
     **/
    public void put(String key, Object object) {
        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else {
            editor.putString(key, object.toString());
        }
        editor.apply();
    }


    /**
     * 取
     *
     * @param key
     * @param object 默认值
     **/
    public Object get(String key, Object object) {
        if (object instanceof String) {
            return sharedPreferences.getString(key, (String) object);
        } else if (object instanceof Integer) {
            return sharedPreferences.getInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            return sharedPreferences.getBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            return sharedPreferences.getFloat(key, (Float) object);
        } else if (object instanceof Long) {
            return sharedPreferences.getLong(key, (Long) object);
        } else {
            return sharedPreferences.getString(key, null);
        }
    }


    /**
     * 移除某个key值已经对应的值
     *
     * @param key
     */
    public void remove(String key) {
        editor.remove(key);
        editor.apply();
    }



    /**
     * 清除所有的数据
     */
    public void clear() {
        editor.clear();
        editor.apply();
    }


    /**
     * 查询某个key是否存在
     *
     * @param key
     * @return
     */
    public boolean contains(String key) {
        return sharedPreferences.contains(key);
    }


    /**
     * 返回所有的键值对
     *
     * @return
     */
    public Map<String, ?> getAll() {
        return sharedPreferences.getAll();
    }


}
