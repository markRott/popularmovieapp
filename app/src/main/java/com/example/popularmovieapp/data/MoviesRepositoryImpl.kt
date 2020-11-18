package com.example.popularmovieapp.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.flowable
import com.example.popularmovieapp.utils.PAGE_SIZE
import com.example.popularmovieapp.entities.ui.MovieItem
import com.example.popularmovieapp.ui.adapter.paging.MovieDataSource
import io.reactivex.Flowable

class MoviesRepositoryImpl(private val movieDataSource: MovieDataSource) : MoviesRepository {

    override fun fetchPopularMovie(): Flowable<PagingData<MovieItem>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = { movieDataSource }
        ).flowable
    }
}