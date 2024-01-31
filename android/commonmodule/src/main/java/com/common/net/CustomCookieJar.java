package com.common.net;

import androidx.annotation.NonNull;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

public class CustomCookieJar implements CookieJar {

    @Override
    public void saveFromResponse(@NonNull HttpUrl url, @NonNull List<Cookie> cookies) {
        if (url.url().getPath().endsWith("user/login") || url.url().getPath().endsWith("user/logout/json")) {
            RetrofitManager.getInstance().saveCookie(cookies);
        }
    }

    @Override
    public List<Cookie> loadForRequest(@NonNull HttpUrl url) {
//        if(url.url().getPath().endsWith("login")) {
//            return localCookies;
//        } else {
//            return Collections.emptyList();
//        }
        return RetrofitManager.getInstance().getCookie();
    }
}
