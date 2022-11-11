package com.ahoy.weathertask.presentation.current

import androidx.lifecycle.ViewModel
import com.mindera.rocketscience.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class CurrentWeatherViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(CurrentWeatherViewModel::class)
    abstract fun bindsCurrentViewModel(currentWeatherViewModel : CurrentWeatherViewModel): ViewModel
}