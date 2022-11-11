package com.ahoy.weathertask.data

import com.ahoy.weathertask.domain.model.response.weatherdata.WeatherData
import com.ahoy.weathertask.domain.model.response.weatherdatabycity.WeatherDataByCity
import com.ahoy.weathertask.domain.model.response.weatherdatabyday.WeatherDataByDay
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceApi {
    @GET("/data/3.0/onecall")
    suspend fun getCurrentWeatherData(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") appId : String ): Response<WeatherData>

    @GET("/data/2.5/forecast/daily")
    suspend fun getWeatherDataByDay(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("cnt") count : Int ,
        @Query("appid") appId : String ): Response<WeatherDataByDay>

    @GET("/data/2.5/weather")
    suspend fun getWeatherDataByCity(
        @Query("q") city : String,
        @Query("appid") appId : String ): Response<WeatherDataByCity>
}