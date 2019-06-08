package com.odhs.gongilproject.api

import android.content.Context
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.odhs.gongilproject.R
import com.odhs.gongilproject.api.interceptor.HeaderInterceptor
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author 오동호 <graystone117@gmail.com>
 * @since 2019-06-08
 */

object ApiProvider {

    fun provideApi(context: Context): ApiSpec {
        return getWeatherApi(OkHttpClient.Builder().addInterceptor(HeaderInterceptor()).build(), context)
    }

    private fun getWeatherApi(client: OkHttpClient, context: Context): ApiSpec {
        return Retrofit.Builder()
            .baseUrl(context.getString(R.string.weather_endpoint))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
            .build()
            .create(ApiSpec::class.java)
    }

    fun <T> request(call: Deferred<Response<T>>,
                    success: ((response: Response<T>) -> Unit),
                    fail: ((throwable: Throwable) -> Unit)? = null) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                success.invoke(call.await())
            } catch (t: Throwable) {
                fail?.invoke(t)
            }
        }
    }
}