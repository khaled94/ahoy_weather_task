package com.ahoy.weathertask.domain.repository

import com.ahoy.weathertask.data.localdb.CityEntity
import com.ahoy.weathertask.domain.model.CityModel
import com.ahoy.weathertask.domain.model.response.weatherdata.WeatherData
import com.ahoy.weathertask.domain.model.response.weatherdatabycity.WeatherDataByCity
import com.ahoy.weathertask.domain.model.response.weatherdatabyday.WeatherDataByDay
import com.ahoy.weathertask.domain.utils.DataState
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getCurrentWeatherData(
        latitude: Double,
        longitude: Double
    ): Flow<DataState<WeatherData>>

    suspend fun getCurrentWeatherDataByDay(
        latitude: Double,
        longitude: Double,
        count: Int
    ): Flow<DataState<WeatherDataByDay>>

    suspend fun getCurrentWeatherDataByCity(
        city: String
    ): Flow<DataState<WeatherDataByCity>>

    suspend fun addFavouritetoDB(cityModel: CityModel)
    suspend fun getFavouritesFromDatabase(): List<CityModel>
    suspend fun getFavouriteCityByID(cityId : Int): CityModel

}