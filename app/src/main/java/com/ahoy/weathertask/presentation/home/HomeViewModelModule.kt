package com.ahoy.weathertask.presentation.home

import androidx.lifecycle.ViewModel
import com.mindera.rocketscience.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class HomeViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindsCurrentViewModel(homeViewModel : HomeViewModel): ViewModel
}