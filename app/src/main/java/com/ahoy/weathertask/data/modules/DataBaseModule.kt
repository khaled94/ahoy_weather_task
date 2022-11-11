package com.ahoy.weathertask.data.modules

import android.app.Application
import androidx.annotation.NonNull
import androidx.room.Room
import com.ahoy.weathertask.data.localdb.WeatherDatabase
import com.ahoy.weathertask.data.localdb.WeatherDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@NonNull application: Application): WeatherDatabase {
        return Room
            .databaseBuilder(application, WeatherDatabase::class.java, "Weather.db")
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideWeatherDao(@NonNull database: WeatherDatabase): WeatherDao {
        return database.weatherDao()
    }

/*    @Provides
    @Singleton
    fun provideDataMapper(@NonNull database: AlarmDatabase): DataMapper {
        return database.dataMapper()
    }*/

}