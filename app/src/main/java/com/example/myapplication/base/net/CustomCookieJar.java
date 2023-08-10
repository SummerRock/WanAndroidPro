package com.example.myapplication.base.net;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

public class CustomCookieJar implements CookieJar {
    private final List<Cookie> localCookies = new ArrayList<>();

    @Override
    public void saveFromResponse(@NonNull HttpUrl url, @NonNull List<Cookie> cookies) {
        if (url.url().getPath().endsWith("login")) {
            localCookies.addAll(cookies);
        }
    }

    @Override
    public List<Cookie> loadForRequest(@NonNull HttpUrl url) {
        if(url.url().getPath().endsWith("login")) {
            return localCookies;
        } else {
            return Collections.emptyList();
        }
    }
}
