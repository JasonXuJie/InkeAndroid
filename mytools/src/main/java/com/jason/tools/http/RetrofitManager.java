package com.jason.tools.http;

import com.jason.tools.BuildConfig;
import com.jason.tools.utils.StringUtil;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jason on 2018/10/23.
 */

public class RetrofitManager {


    private Retrofit retrofit;
    private final long second = 60;
    private static String baseUrl="http://v.juhe.cn/";


    public static RetrofitManager getInstance(){
        return RetrofitHolder.INSTANCE;
    }


    private static class RetrofitHolder{
        private static final RetrofitManager INSTANCE = new RetrofitManager();
    }


    private RetrofitManager(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG){
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
        }
        builder.retryOnConnectionFailure(true);
        builder.connectTimeout(second, TimeUnit.SECONDS);
        builder.writeTimeout(second,TimeUnit.SECONDS);
        builder.readTimeout(second,TimeUnit.SECONDS);
        OkHttpClient client = builder.build();
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
    }


    public Retrofit getRetrofit(){
        return retrofit;
    }

    public ApiService getApi(){
        return retrofit.create(ApiService.class);
    }





}
