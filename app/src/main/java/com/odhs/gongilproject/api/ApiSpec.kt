package com.odhs.gongilproject.api

import com.odhs.gongilproject.model.ForecastSpace
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author 오동호 <graystone117@gmail.com>
 * @since 2019-06-08
 */

interface ApiSpec {

    // 동네예보조회
    @GET("/ForecastSpaceData")
    fun forecastSpace(
        @Path("ServiceKey") serviceKey: String,
        @Path("base_date") baseDate: String,
        @Path("base_time") baseTime: String,
        @Path("nx") nx: Int,
        @Path("ny") ny: Int,
        @Path("numOfRows") numOfRows: Int = 300,
        @Path("pageNo") pageNumber: Int = 1,
        @Path("_type") type: String = "json"): Deferred<Response<ForecastSpace>>
}