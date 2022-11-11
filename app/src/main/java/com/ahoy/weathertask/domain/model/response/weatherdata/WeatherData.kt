package com.ahoy.weathertask.domain.model.response.weatherdata

data class WeatherData(
    val lat: Double,
    val lon: Double,
    val timezone: String,
    val timezone_offset: Int,
    val current: Current,
    val minutely: List<Minutely>
)