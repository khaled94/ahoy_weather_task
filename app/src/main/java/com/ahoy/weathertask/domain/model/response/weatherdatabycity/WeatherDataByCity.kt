package com.ahoy.weathertask.domain.model.response.weatherdatabycity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class WeatherDataByCity(
    @SerializedName("coord")
    val coord: @RawValue Coord,
    @SerializedName("weather")
    val weather: @RawValue List<Weather>,
    @SerializedName("base")
    val base: String,
    @SerializedName("main")
    val main: @RawValue Main,
    @SerializedName("visibility")
    val visibility: Int,
    @SerializedName("wind")
    val wind: @RawValue Wind,
    @SerializedName("clouds")
    val clouds: @RawValue Clouds,
    @SerializedName("dt")
    val dt: Int,
    @SerializedName("sys")
    val sys: @RawValue Sys,
    @SerializedName("timezone")
    val timezone: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("cod")
    val cod: Int
) : Parcelable