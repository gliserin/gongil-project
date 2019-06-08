package com.odhs.gongilproject.model.forecastSpace

import com.google.gson.annotations.SerializedName

/**
 * @author 오동호 <graystone117@gmail.com>
 * @since 2019-06-09
 */

data class Response(
    @SerializedName("header") var header: Header,
    @SerializedName("body") var body: Body
)