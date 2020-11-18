package com.example.popularmovieapp.ui.adapter.paging

import androidx.paging.rxjava2.RxPagingSource
import com.example.popularmovieapp.api.AppApi
import com.example.popularmovieapp.entities.toDomain
import com.example.popularmovieapp.entities.ui.MovieUiData
import com.example.popularmovieapp.entities.ui.MoviesUiData
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieDataSource(private val api: AppApi) : RxPagingSource<Int, MovieUiData>() {

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, MovieUiData>> {
        val page: Int = params.key ?: 1
        return api
                .fetchPopularMovie(page)
                .subscribeOn(Schedulers.io())
                .map { it.toDomain() }
                .map { toLoadResult(it) }
                .onErrorReturn { LoadResult.Error(it) }
    }

    private fun toLoadResult(data: MoviesUiData): LoadResult<Int, MovieUiData> {
        return LoadResult.Page(
                data.results,
                null,
                nextKey = data.page + 1
        )
    }
}