package com.ahoy.weathertask.presentation.current

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahoy.weathertask.domain.model.response.weatherdata.WeatherData
import com.ahoy.weathertask.domain.model.response.weatherdatabyday.WeatherDataByDay
import com.ahoy.weathertask.domain.repository.Repository
import com.ahoy.weathertask.domain.utils.DataState
import kotlinx.coroutines.launch
import javax.inject.Inject

class CurrentWeatherViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val weatherData = MutableLiveData<DataState<WeatherData>>()
    val weatherDataByDay = MutableLiveData<DataState<WeatherDataByDay>>()

    fun getCurrentWeatherData(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            repository.getCurrentWeatherData(latitude, longitude).collect { dataState ->
                weatherData.postValue(dataState)
            }
        }
    }

    fun getWeatherDataByDay(latitude: Double, longitude: Double, count: Int) {
        viewModelScope.launch {
            repository.getCurrentWeatherDataByDay(latitude, longitude, count).collect { dataState ->
                weatherDataByDay.postValue(dataState)
            }
        }

    }
}
