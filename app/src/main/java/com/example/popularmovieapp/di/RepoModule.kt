package com.example.popularmovieapp.di

import com.example.popularmovieapp.api.AppApi
import com.example.popularmovieapp.data.RepositoryContract
import com.example.popularmovieapp.data.PopularMovieRepository
import com.example.popularmovieapp.thread.ThreadContract
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class RepoModule {

    @Provides
    @Singleton
    fun providePopularMovieRepository(
        api: AppApi,
        thread: ThreadContract
    ): RepositoryContract =
        PopularMovieRepository(api, thread)

}