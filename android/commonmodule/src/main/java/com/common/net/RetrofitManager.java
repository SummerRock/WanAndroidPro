package com.common.net;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.common.json.GsonHelper;
import com.common.storage.MMKVHelper;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {

    private static final String BASE_URL = "https://www.wanandroid.com/";

    /**
     * 未登录的错误码
     */
    public static final int NOT_LOGON_ERROR_CODE = -1001;

    private static Retrofit retrofit;

    private RetrofitManager() {
        // 私有的构造方法，防止外部实例化
        if (retrofit == null) {
            // 创建自定义拦截器
            CookieInterceptor cookieInterceptor = new CookieInterceptor();
            // 创建 OkHttpClient 实例并添加拦截器
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .cookieJar(new CustomCookieJar())
                    .addInterceptor(cookieInterceptor) // 添加自定义拦截器
                    .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
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

    private static final String COOKIE_KEY = "local_cookie";

    public void clearCookie() {
        MMKVHelper.INSTANCE.getDefaultMMKV().remove(COOKIE_KEY);
    }

    public void saveCookie(@NonNull List<Cookie> localCookies) {
        MMKVHelper.INSTANCE.getDefaultMMKV().encode(COOKIE_KEY, GsonHelper.toJson(localCookies));
    }

    public List<Cookie> getCookie() {
        String cookieStr = MMKVHelper.INSTANCE.getDefaultMMKV().decodeString(COOKIE_KEY, "");
        if (TextUtils.isEmpty(cookieStr)) {
            return Collections.emptyList();
        } else {
            // 使用TypeToken来处理泛型类型
            Type listType = new TypeToken<List<Cookie>>() {
            }.getType();
            List<Cookie> localCookies = GsonHelper.fromJson(cookieStr, listType);
            return localCookies;
        }
    }
}
