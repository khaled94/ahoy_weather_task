package com.ahoy.weathertask.data.localdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ahoy.weathertask.domain.model.CityModel

@Database(entities = [CityEntity::class], version = 1, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun weatherDao() : WeatherDao
}
