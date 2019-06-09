package com.odhs.gongilproject

import android.content.Context
import com.odhs.gongilproject.api.ApiProvider
import com.odhs.gongilproject.api.ApiSpec
import kotlinx.coroutines.Deferred
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author 오동호 <graystone117@gmail.com>
 * @since 2019-06-08
 */

val Context.apiWeather: ApiSpec
    get() = ApiProvider.provideApi(this)

fun <T> request(call: Deferred<Response<T>>, success: ((response: Response<T>) -> Unit), fail: ((throwable: Throwable) -> Unit)? = null) {
    ApiProvider.request(call, success, fail)
}
