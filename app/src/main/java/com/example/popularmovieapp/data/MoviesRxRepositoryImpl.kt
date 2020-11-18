package com.example.popularmovieapp.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.flowable
import com.example.popularmovieapp.entities.ui.MovieUiData
import com.example.popularmovieapp.ui.adapter.paging.MovieDataSource
import io.reactivex.Flowable

class MoviesRxRepositoryImpl(private val movieDataSource: MovieDataSource) : MoviesRxRepository {

    override fun fetchPopularMovie(): Flowable<PagingData<MovieUiData>> {
        return Pager(
                config = PagingConfig(
                        pageSize = 20,
                        enablePlaceholders = true,
                        maxSize = 30,
                        prefetchDistance = 5,
                        initialLoadSize = 40
                ),
                pagingSourceFactory = { movieDataSource }
        ).flowable
    }
}