package com.ahoy.weathertask.domain.model.response.weatherdatabyday

data class WeatherDataByDay(
    val city: City,
    val cod: String,
    val message: Double,
    val cnt: Int,
    val list: List<DayData>
)