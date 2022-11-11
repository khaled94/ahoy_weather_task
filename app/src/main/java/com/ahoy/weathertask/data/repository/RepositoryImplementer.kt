package com.ahoy.weathertask.data.repository

import com.ahoy.weathertask.data.datastore.DataSource
import com.ahoy.weathertask.data.localdb.CityEntity
import com.ahoy.weathertask.domain.model.CityModel
import com.ahoy.weathertask.domain.model.response.weatherdata.WeatherData
import com.ahoy.weathertask.domain.model.response.weatherdatabycity.WeatherDataByCity
import com.ahoy.weathertask.domain.model.response.weatherdatabyday.WeatherDataByDay
import com.ahoy.weathertask.domain.repository.Repository
import com.ahoy.weathertask.domain.utils.DataState
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImplementer @Inject constructor(
    private val remoteDataSource: DataSource,
    private val localDataSource: DataSource,
) : Repository {


    override suspend fun getCurrentWeatherData(
        latitude: Double,
        longitude: Double
    ): Flow<DataState<WeatherData>> {
        return remoteDataSource.getCurrentWeatherData(latitude, longitude)
    }

    override suspend fun getCurrentWeatherDataByDay(
        latitude: Double,
        longitude: Double,
        count: Int
    ): Flow<DataState<WeatherDataByDay>> {
        return remoteDataSource.getCurrentWeatherDataByDay(latitude, longitude, count)
    }

    override suspend fun getCurrentWeatherDataByCity(
        city: String
    ): Flow<DataState<WeatherDataByCity>> {
        return remoteDataSource.getCurrentWeatherDataByCity(city)
    }

    override suspend fun addFavouritetoDB(cityModel: CityModel) =
        localDataSource.addFavouritetoDB(cityModel)

    override suspend fun getFavouritesFromDatabase(): List<CityModel> {
        return localDataSource.getFavouritesFromDatabase()
    }

    override suspend fun getFavouriteCityByID(cityId: Int): CityModel {
        return localDataSource.getFavouriteCityByID(cityId)
    }

}
