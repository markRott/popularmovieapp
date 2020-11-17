package com.example.popularmovieapp.data

import com.example.popularmovieapp.api.AppApi
import com.example.popularmovieapp.entities.PopularMovieResponse
import com.example.popularmovieapp.thread.ThreadContract
import io.reactivex.Single

class PopularMovieRepository(
    private val api: AppApi,
    private val thread: ThreadContract
) : RepositoryContract {

    override fun fetchPopularMovie(page: Int): Single<PopularMovieResponse> {
        return api
            .fetchPopularMovie(page)
            .subscribeOn(thread.bg())
            .doOnEvent { t1, t2 ->
                println("7777 Thread: ${Thread.currentThread().name}")
            }
            .observeOn(thread.ui())
    }
}