package com.ahoy.weathertask.data.datastore

import com.ahoy.weathertask.data.Constants
import com.ahoy.weathertask.data.ServiceApi
import com.ahoy.weathertask.data.localdb.CityEntity
import com.ahoy.weathertask.domain.model.CityModel
import com.ahoy.weathertask.domain.utils.DataState
import io.reactivex.Single
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteDataSource @Inject constructor
    (private val serviceApi: ServiceApi) : DataSource {
    override suspend fun getCurrentWeatherData(
        latitude: Double,
        longitude: Double
    ) = flow {
        emit(DataState.Loading)
        try {
            val details =
                serviceApi.getCurrentWeatherData(
                    latitude = latitude,
                    longitude = longitude,
                    appId = Constants.appID
                )
            if (details.isSuccessful) {
                emit(DataState.Success(details.body()!!))
            }
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    override suspend fun getCurrentWeatherDataByDay(
        latitude: Double,
        longitude: Double,
        count: Int
    ) = flow {
        emit(DataState.Loading)
        try {
            val details =
                serviceApi.getWeatherDataByDay(
                    latitude = latitude,
                    longitude = longitude,
                    count = count,
                    appId = Constants.appID
                )
            if (details.isSuccessful) {
                emit(DataState.Success(details.body()!!))
            }
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    override suspend fun getCurrentWeatherDataByCity(
        city: String
    ) = flow {
        emit(DataState.Loading)
        try {
            val details =
                serviceApi.getWeatherDataByCity(
                    city = city,
                    appId = Constants.appID
                )
            if (details.isSuccessful) {
                emit(DataState.Success(details.body()!!))
            }
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    override suspend fun addFavouritetoDB(cityModel: CityModel ){
        TODO("Not yet implemented")
    }

    override suspend fun getFavouritesFromDatabase(): List<CityModel> {
        TODO("Not yet implemented")
    }

    override suspend fun getFavouriteCityByID(cityId: Int): CityModel {
        TODO("Not yet implemented")
    }


}