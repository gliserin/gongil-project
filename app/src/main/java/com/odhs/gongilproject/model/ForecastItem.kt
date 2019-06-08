package com.odhs.gongilproject.model

import com.google.gson.annotations.SerializedName

/**
 * @author 오동호 <graystone117@gmail.com>
 * @since 2019-06-08
 */
 
data class ForecastItem(
    @SerializedName("baseDate") var baseDate: Int,
    @SerializedName("baseTime") var baseTime: String,
    @SerializedName("category") var category: String,
    @SerializedName("fcstDate") var forecastDate: Int,
    @SerializedName("fcstTime") var forecastTime: Int,
    @SerializedName("fcstValue") var forecastValue: Double
)