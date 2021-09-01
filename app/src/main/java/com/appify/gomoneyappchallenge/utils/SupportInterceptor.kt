package com.appify.gomoneyappchallenge.utils

import okhttp3.Interceptor
import okhttp3.Response

class SupportInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .addHeader("X-Auth-Token", "a199ae5a0aa84b3eb426eb02b0d8e71a")
                .build()
        return chain.proceed(request)
    }
}