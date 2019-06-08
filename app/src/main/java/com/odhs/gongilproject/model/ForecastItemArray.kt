package com.odhs.gongilproject.model

import com.google.gson.annotations.SerializedName

/**
 * @author 오동호 <graystone117@gmail.com>
 * @since 2019-06-08
 */
 
data class ForecastItemArray(
    @SerializedName("item") var itemArray: ArrayList<ForecastItem>
)