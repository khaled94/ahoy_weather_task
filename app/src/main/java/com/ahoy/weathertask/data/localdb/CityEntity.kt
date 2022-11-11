package com.ahoy.weathertask.data.localdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Favourites")
data class CityEntity (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    var id: Int? = null,

    @ColumnInfo(name = "Name")
    var Name: String,

    @ColumnInfo(name = "Country")
    var Country: String,

    @ColumnInfo(name = "Humidity")
    var Humidity: Int,

    @ColumnInfo(name = "Temp")
    var Temp: Double,

    )