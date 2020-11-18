//package com.example.popularmovieapp.data
//
//import com.example.popularmovieapp.api.AppApi
//import com.example.popularmovieapp.entities.toDomain
//import com.example.popularmovieapp.entities.ui.MoviesUiData
//import com.example.popularmovieapp.thread.ThreadContract
//import io.reactivex.Single
//
//class PopularMovieRepository(
//    private val api: AppApi,
//    private val thread: ThreadContract
//) : RepositoryContract {
//
//    override fun fetchPopularMovie(page: Int): Single<MoviesUiData> {
//        return api
//            .fetchPopularMovie(page)
//            .subscribeOn(thread.bg())
//            .map { it.toDomain() }
//            .observeOn(thread.ui())
//    }
//}