package com.example.myapplication.base.net;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {

    private static final String BASE_URL = "https://www.wanandroid.com/";
    private static Retrofit retrofit;

    private RetrofitManager() {
        // 私有的构造方法，防止外部实例化
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
    }

    private static final class InstanceHolder {
        static final RetrofitManager instance = new RetrofitManager();
    }


    public static RetrofitManager getInstance() {
        return RetrofitManager.InstanceHolder.instance;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
