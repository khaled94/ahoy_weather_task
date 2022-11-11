package com.ahoy.weathertask.di

import com.ahoy.weathertask.MainActivity
import com.ahoy.weathertask.presentation.current.CurrentWeatherFragment
import com.ahoy.weathertask.presentation.home.HomeFragment
import com.ahoy.weathertask.presentation.current.CurrentWeatherViewModelModule
import com.ahoy.weathertask.presentation.favourite.FavouriteFragment
import com.ahoy.weathertask.presentation.favourite.FavouriteViewModelModule
import com.ahoy.weathertask.presentation.favouritedetails.FavouriteDetailsFragment
import com.ahoy.weathertask.presentation.home.HomeViewModelModule
import com.ahoy.weathertask.presentation.search.SearchFragment
import com.ahoy.weathertask.presentation.search.SearchViewModelModule
import com.ahoy.weathertask.presentation.settings.SettingsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

   @ContributesAndroidInjector
   abstract fun contributeMainActivity() : MainActivity

   @ContributesAndroidInjector(modules = [HomeViewModelModule::class])
   abstract fun contributeHomeFragment(): HomeFragment

   @ContributesAndroidInjector(modules = [CurrentWeatherViewModelModule::class])
   abstract fun contributeCurrentWeatherFragment(): CurrentWeatherFragment

   @ContributesAndroidInjector(modules = [SearchViewModelModule::class])
   abstract fun contributeSearchFragment(): SearchFragment

   @ContributesAndroidInjector(modules = [FavouriteViewModelModule::class])
   abstract fun contributeFavouriteFragment(): FavouriteFragment

   @ContributesAndroidInjector()
   abstract fun contributeSettingsFragment(): SettingsFragment

   @ContributesAndroidInjector()
   abstract fun contributeFavouriteDetailsFragment(): FavouriteDetailsFragment


}