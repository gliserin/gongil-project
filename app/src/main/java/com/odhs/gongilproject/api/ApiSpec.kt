package com.odhs.gongilproject.api

import com.odhs.gongilproject.model.forecastSpace.ForecastSpace
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author 오동호 <graystone117@gmail.com>
 * @since 2019-06-08
 */

interface ApiSpec {

    // 동네예보조회

    @GET("ForecastSpaceData")
    fun forecastSpace(
        @Query("serviceKey", encoded = true) serviceKey: String,
        @Query("base_date") baseDate: String,
        @Query("base_time") baseTime: String,
        @Query("nx") nx: Int,
        @Query("ny") ny: Int,
        @Query("numOfRows") numOfRows: Int,
        @Query("pageNo") pageNumber: Int,
        @Query("_type") type: String
    ): Deferred<Response<ForecastSpace>>
}