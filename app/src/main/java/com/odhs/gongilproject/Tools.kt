package com.odhs.gongilproject

import android.content.Context
import com.odhs.gongilproject.api.ApiProvider
import com.odhs.gongilproject.api.ApiSpec

/**
 * @author 오동호 <graystone117@gmail.com>
 * @since 2019-06-08
 */

val Context.api: ApiSpec
    get() = ApiProvider.