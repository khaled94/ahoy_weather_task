package com.ahoy.weathertask.presentation.favourite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahoy.weathertask.domain.model.CityModel
import com.ahoy.weathertask.domain.repository.Repository
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavouriteViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val favourites = MutableLiveData<List<CityModel>>()
    var city = MutableLiveData<CityModel>()

    fun getFavourites() {
        viewModelScope.launch {
            favourites.postValue(repository.getFavouritesFromDatabase())
        }
    }

    fun getFavouriteCityByID(cityId: Int) {
        viewModelScope.launch {
            city.postValue(repository.getFavouriteCityByID(cityId))
        }
    }
}