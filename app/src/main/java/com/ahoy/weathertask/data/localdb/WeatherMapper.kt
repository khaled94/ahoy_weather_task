package com.ahoy.weathertask.data.localdb

import com.ahoy.weathertask.domain.model.CityModel

class WeatherMapper {

   fun mapFromEntityToModel(entity: CityEntity): CityModel {
         return CityModel(
             entity.id!!,
             entity.Name,
             entity.Country,
             entity.Humidity,
             entity.Temp
         )
    }


    fun mapFromModelToEntity(model: CityModel): CityEntity {
        return CityEntity(
            model.id!!,
            model.Name,
            model.Country,
            model.Humidity,
            model.Temp
        )
    }


    //fun List<AlarmListResponseItem>.toAlarmTableModelList() = this.map { it.toAlarmTableModel() }*/
}

