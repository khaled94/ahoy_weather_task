package com.ahoy.weathertask.domain.model.response.weatherdatabyday

data class FeelsLike(
    val day: Double,
    val night: Double,
    val eve: Double,
    val morn: Double
)