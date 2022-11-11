package com.ahoy.weathertask.presentation.search

import androidx.lifecycle.ViewModel
import com.ahoy.weathertask.presentation.favourite.FavouriteViewModel
import com.mindera.rocketscience.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SearchViewModelModule{
    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindSearchViewModel(searchViewModel: SearchViewModel): ViewModel
}