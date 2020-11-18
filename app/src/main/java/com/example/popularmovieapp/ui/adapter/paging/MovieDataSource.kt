package com.example.popularmovieapp.ui.adapter.paging

import android.util.Log
import androidx.paging.rxjava2.RxPagingSource
import com.example.popularmovieapp.api.AppApi
import com.example.popularmovieapp.entities.toDomain
import com.example.popularmovieapp.entities.ui.MovieItem
import com.example.popularmovieapp.entities.ui.MoviesData
import com.example.popularmovieapp.thread.ThreadContract
import com.example.popularmovieapp.utils.APP_TAG
import io.reactivex.Single

class MovieDataSource(
    private val api: AppApi,
    private val thread: ThreadContract
) : RxPagingSource<Int, MovieItem>() {

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, MovieItem>> {
        val page: Int = params.key ?: 1
        return api
            .fetchPopularMovie(page)
            .subscribeOn(thread.bg())
            .map { it.toDomain() }
            .map { toLoadResult(it) }
            .doOnSuccess { Log.d(APP_TAG, "Fetch new movies data") }
            .onErrorReturn { LoadResult.Error(it) }
    }

    private fun toLoadResult(data: MoviesData): LoadResult<Int, MovieItem> {
        return LoadResult.Page(
            data.results,
            null,
            nextKey = data.page + 1
        )
    }
}