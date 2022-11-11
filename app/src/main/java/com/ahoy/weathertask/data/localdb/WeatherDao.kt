package com.ahoy.weathertask.data.localdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WeatherDao {

   @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(data: CityEntity)

    @Query("SELECT * from Favourites")
    suspend fun getAllFavourites(): List<CityEntity>

   // @Query("DELETE FROM Alarms")
   // fun nukeTable()
   @Query("SELECT * FROM Favourites WHERE Id==:cityId")
   suspend fun findCity(cityId:Int):CityEntity

}