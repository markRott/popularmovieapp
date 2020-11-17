package com.example.popularmovieapp.di

import com.example.popularmovieapp.thread.ThreadContract
import com.example.popularmovieapp.thread.ThreadImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class ThreadModule {

    @Singleton
    @Provides
    fun provideThread(): ThreadContract = ThreadImpl()
}