package com.odhs.gongilproject.model.forecastSpace

import com.google.gson.annotations.SerializedName

/**
 * @author 오동호 <graystone117@gmail.com>
 * @since 2019-06-08
 */

data class Body(
    @SerializedName("items") var items: ItemArray,
    @SerializedName("numOfRows") var numOfRows: Int,
    @SerializedName("pageNo") var pageNo: Int,
    @SerializedName("totalCount") var totalCount: Int
)