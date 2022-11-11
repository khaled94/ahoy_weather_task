package com.ahoy.weathertask.presentation.home

import androidx.lifecycle.ViewModel
import com.ahoy.weathertask.domain.repository.Repository
import com.ahoy.weathertask.presentation.utils.HomeFeature
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val features = mutableListOf<HomeFeature>()

    init {
        val feature1 = HomeFeature("Current Weather")
        val feature2 = HomeFeature("Search")
        val feature3 = HomeFeature("Favourite")
        val feature4 = HomeFeature("Settings")
        features.add(feature1)
        features.add(feature2)
        features.add(feature3)
        features.add(feature4)
    }

}