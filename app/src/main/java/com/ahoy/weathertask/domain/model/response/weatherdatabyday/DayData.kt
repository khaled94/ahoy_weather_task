package com.ahoy.weathertask.domain.model.response.weatherdatabyday

data class DayData(
    val dt: Int,
    val sunrise: Int,
    val sunset: Int,
    val temp: Temp,
    val feels_like: FeelsLike,
    val pressure: Int,
    val humidity: Int,
    val weather: List<Weather>,
    val speed: Double,
    val deg: Int,
    val gust: Double,
    val clouds: Int,
    val pop: Double,
    val rain: Double
)