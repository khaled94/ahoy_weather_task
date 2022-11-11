package com.ahoy.weathertask.presentation.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahoy.weathertask.data.localdb.CityEntity
import com.ahoy.weathertask.domain.model.CityModel
import com.ahoy.weathertask.domain.model.response.weatherdatabycity.WeatherDataByCity
import com.ahoy.weathertask.domain.model.response.weatherdatabyday.WeatherDataByDay
import com.ahoy.weathertask.domain.repository.Repository
import com.ahoy.weathertask.domain.utils.DataState
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val weatherDataByCity = MutableLiveData<DataState<WeatherDataByCity>>()
    val weatherDataByDay = MutableLiveData<DataState<WeatherDataByDay>>()

    fun getCurrentWeatherDataByCity(city: String) {
        viewModelScope.launch {
            repository.getCurrentWeatherDataByCity(city).collect { dataState ->
                weatherDataByCity.postValue(dataState)
            }
        }
    }

    fun AddFavouritetoDB(cityModel: CityModel){
      viewModelScope.launch {
          repository.addFavouritetoDB(cityModel)
      }
    }


}