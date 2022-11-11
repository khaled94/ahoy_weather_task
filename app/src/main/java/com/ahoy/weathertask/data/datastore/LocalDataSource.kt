package com.ahoy.weathertask.data.datastore

import com.ahoy.weathertask.data.localdb.CityEntity
import com.ahoy.weathertask.data.localdb.WeatherDao
import com.ahoy.weathertask.data.localdb.WeatherMapper
import com.ahoy.weathertask.domain.model.CityModel
import com.ahoy.weathertask.domain.model.response.weatherdata.WeatherData
import com.ahoy.weathertask.domain.model.response.weatherdatabycity.WeatherDataByCity
import com.ahoy.weathertask.domain.model.response.weatherdatabyday.WeatherDataByDay
import com.ahoy.weathertask.domain.utils.DataState
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor
    (
    private val weatherDao: WeatherDao,
   private val weatherMapper: WeatherMapper = WeatherMapper()
) : DataSource {
    override suspend fun getCurrentWeatherData(
        latitude: Double,
        longitude: Double
    ): Flow<DataState<WeatherData>> {
        TODO("Not yet implemented")
    }

    override suspend fun getCurrentWeatherDataByDay(
        latitude: Double,
        longitude: Double,
        count: Int
    ): Flow<DataState<WeatherDataByDay>> {
        TODO("Not yet implemented")
    }

    override suspend fun getCurrentWeatherDataByCity(
        city: String
    ): Flow<DataState<WeatherDataByCity>> {
        TODO("Not yet implemented")
    }

    override suspend fun addFavouritetoDB(cityModel: CityModel) {
        val cityEntity = weatherMapper.mapFromModelToEntity(cityModel)
        weatherDao.insertData(cityEntity)
    }

    override suspend fun getFavouritesFromDatabase(): List<CityModel> {
        val cityList = weatherDao.getAllFavourites().map {
            weatherMapper.mapFromEntityToModel(it)
        }
        return cityList
    }

    override suspend fun getFavouriteCityByID(cityId: Int): CityModel {
        return weatherMapper.mapFromEntityToModel(weatherDao.findCity(cityId))
    }


}