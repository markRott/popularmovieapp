package com.example.popularmovieapp.ui.adapter.paging

import androidx.paging.rxjava2.RxPagingSource
import com.example.popularmovieapp.data.RepositoryContract
import com.example.popularmovieapp.entities.ui.MovieUiData
import com.example.popularmovieapp.entities.ui.MoviesUiData
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class MovieDataSource(private val repository: RepositoryContract) : RxPagingSource<Int, MovieUiData>() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, MovieUiData>> {

        val nextPageNumber: Int = params.key ?: 1
        return repository
            .fetchPopularMovie(nextPageNumber)
            .map { toLoadResult(it) }
    }


    private fun toLoadResult(data: MoviesUiData): LoadResult<Int, MovieUiData> {
        return LoadResult.Page(
            data.results,
            null,
            nextKey = data.page + 1
        )
    }
}