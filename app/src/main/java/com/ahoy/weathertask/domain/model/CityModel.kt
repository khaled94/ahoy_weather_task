package com.ahoy.weathertask.domain.model

import android.os.Parcel
import android.os.Parcelable

data class CityModel(
    var id: Int? = null,
    var Name: String,
    var Country: String,
    var Humidity: Int,
    var Temp: Double,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readDouble()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(Name)
        parcel.writeString(Country)
        parcel.writeInt(Humidity)
        parcel.writeDouble(Temp)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CityModel> {
        override fun createFromParcel(parcel: Parcel): CityModel {
            return CityModel(parcel)
        }

        override fun newArray(size: Int): Array<CityModel?> {
            return arrayOfNulls(size)
        }
    }
}