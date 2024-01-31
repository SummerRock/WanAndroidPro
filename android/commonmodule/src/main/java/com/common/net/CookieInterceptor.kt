package com.common.net

import okhttp3.Interceptor
import okhttp3.Response

class CookieInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        // 执行请求
        val response = chain.proceed(request)

        // 获取响应中的 Cookie
        val cookies = response.headers("Set-Cookie")
        for (cookie in cookies) {
            // 处理每个 Cookie
            println("Received Cookie: $cookie")
        }

        return response
    }
}