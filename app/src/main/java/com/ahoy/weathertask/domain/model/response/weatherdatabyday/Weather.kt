package com.ahoy.weathertask.domain.model.response.weatherdatabyday

data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)