package com.example.popularmovieapp.di

import com.example.popularmovieapp.BuildConfig
import com.example.popularmovieapp.api.AppApi
import com.example.popularmovieapp.api.ParamsInterceptor
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideApi(): AppApi {
        val retrofit = initRetrofit()
        return retrofit.create(AppApi::class.java)
    }

    private fun initRetrofit(): Retrofit {
        val retrofitBuilder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(initConverterFactory())
            .addCallAdapterFactory(initCallAdapterFactory())
            .client(initOkHttpClient())
        return retrofitBuilder.build()
    }

    private fun initConverterFactory(): Converter.Factory = GsonConverterFactory.create()

    private fun initCallAdapterFactory(): CallAdapter.Factory = RxJava2CallAdapterFactory.create()

    private fun initOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = (HttpLoggingInterceptor.Level.BODY)

        val builder = OkHttpClient.Builder()
        builder.addInterceptor(logging)
        builder.addInterceptor(ParamsInterceptor())
        return builder.build()
    }
}