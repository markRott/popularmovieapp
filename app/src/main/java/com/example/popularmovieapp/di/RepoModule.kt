package com.example.popularmovieapp.di

import com.example.popularmovieapp.api.AppApi
import com.example.popularmovieapp.data.MoviesRxRepository
import com.example.popularmovieapp.data.MoviesRxRepositoryImpl
import com.example.popularmovieapp.thread.ThreadContract
import com.example.popularmovieapp.ui.adapter.paging.MovieDataSource
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
    fun provideMovieDataSource(
            api: AppApi,
            thread: ThreadContract
    ): MovieDataSource = MovieDataSource(api, thread)

    @Provides
    @Singleton
    fun providePopularMovieRepository(movieDataSource: MovieDataSource): MoviesRxRepository
            = MoviesRxRepositoryImpl(movieDataSource)
}