package com.ahoy.weathertask.data.modules

import android.app.Application
import com.ahoy.weathertask.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class HttpModule {
    @Provides
    @Singleton
    fun provideFile(application: Application): File {
        val file = File(application.filesDir, "cache_dir")
        if (!file.exists())
            file.mkdirs()
        return file
    }

    @Provides
    @Singleton
    fun provideCache(file: File): Cache {
        return Cache(file, (10 * 1000 * 1000).toLong())  // 10 MiB cache
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if(BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return logging
    }

    @Singleton
    @Provides
    fun providesApiHolder() = RemoteApiHolder()

    @Singleton
    @Provides
    fun getAuthorizedOkHttpClient(
        cache: Cache,
        interceptor: HttpLoggingInterceptor,
    ): OkHttpClient {

        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS) // write timeout
            .readTimeout(30, TimeUnit.SECONDS) // read timeout
            .cache(cache)
            .addInterceptor(interceptor)
            .addInterceptor(Interceptor { chain ->
                val original: Request = chain.request()
                // Request customization: add request headers
                val requestBuilder: Request.Builder = original.newBuilder()
                    .header("appid", "d171f3f96693d539da5e1abceb26c482") // <-- this is the important line
                val request: Request = requestBuilder.build()
                chain.proceed(request)
            })
            .build()
    }
}