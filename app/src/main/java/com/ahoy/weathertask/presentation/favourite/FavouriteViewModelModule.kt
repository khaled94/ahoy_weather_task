package com.ahoy.weathertask.presentation.favourite

import androidx.lifecycle.ViewModel
import com.ahoy.weathertask.presentation.current.CurrentWeatherViewModel
import com.mindera.rocketscience.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FavouriteViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(FavouriteViewModel::class)
    abstract fun bindsFavouriteViewModel(favouriteViewModel: FavouriteViewModel): ViewModel
}