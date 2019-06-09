package com.odhs.gongilproject.model.forecastSpace

import com.google.gson.annotations.SerializedName

/**
 * @author 오동호 <graystone117@gmail.com>
 * @since 2019-06-08
 */

data class Item(
    @SerializedName("baseDate") var baseDate: Int,
    @SerializedName("baseTime") var baseTime: String,
    @SerializedName("category") var category: String,
    @SerializedName("fcstDate") var forecastDate: String,
    @SerializedName("fcstTime") var forecastTime: String,
    @SerializedName("fcstValue") var forecastValue: Double
)