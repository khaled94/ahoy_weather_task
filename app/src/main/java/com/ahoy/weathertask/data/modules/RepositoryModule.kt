package com.ahoy.weathertask.data.modules

import com.ahoy.weathertask.data.ServiceApi
import com.ahoy.weathertask.data.datastore.DataSource
import com.ahoy.weathertask.data.datastore.LocalDataSource
import com.ahoy.weathertask.data.datastore.RemoteDataSource
import com.ahoy.weathertask.data.localdb.WeatherDao
import com.ahoy.weathertask.data.repository.RepositoryImplementer
import com.ahoy.weathertask.domain.repository.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    @Named(DI.REMOTE_DATA_SOURCE)
    fun provideRemoteDataSource(serviceApi: ServiceApi): DataSource =
        RemoteDataSource(serviceApi = serviceApi)

    @Provides
    @Singleton
    @Named(DI.LOCAL_DATA_SOURCE)
    fun provideLocalDataSource(weatherDao: WeatherDao): DataSource =
    LocalDataSource(weatherDao = weatherDao)


    @Singleton
    @Provides
    fun providesRemoteRepository(
        @Named(DI.REMOTE_DATA_SOURCE) remoteDataSource: DataSource,
        @Named(DI.LOCAL_DATA_SOURCE) localDataSource: DataSource,
    ): Repository =
        RepositoryImplementer(
            remoteDataSource = remoteDataSource,
            localDataSource = localDataSource,
        )
}