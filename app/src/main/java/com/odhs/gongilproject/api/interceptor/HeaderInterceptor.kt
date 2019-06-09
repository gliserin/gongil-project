package com.odhs.gongilproject.api.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * @author 오동호 <graystone117@gmail.com>
 * @since 2019-06-08
 */

class HeaderInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
            .addHeader("Content-type", "application/json; charset=utf-8")
        return chain.proceed(builder.build())
    }
}