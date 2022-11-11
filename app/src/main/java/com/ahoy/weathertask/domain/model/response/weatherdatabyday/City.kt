package com.ahoy.weathertask.domain.model.response.weatherdatabyday

data class City(
    val id: Int,
    val name: String,
    val coord: Coord,
    val country: String,
    val population: Int,
    val timezone: Int
)