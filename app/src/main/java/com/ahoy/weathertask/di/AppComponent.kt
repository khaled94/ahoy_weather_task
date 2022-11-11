package com.ahoy.weathertask.di

import android.app.Application
import com.ahoy.weathertask.App
import com.ahoy.weathertask.data.modules.*
import com.mindera.rocketscience.di.ViewModelFactory
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ViewModelFactory::class,
        ActivityBuildersModule::class,
        AppModule::class,
        HttpModule::class,
        RetrofitModule::class,
        ServiceModule::class,
        RepositoryModule::class,
        DataBaseModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}