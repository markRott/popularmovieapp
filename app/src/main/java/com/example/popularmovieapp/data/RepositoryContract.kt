package com.example.popularmovieapp.data

import com.example.popularmovieapp.entities.PopularMovieResponse
import io.reactivex.Single

interface RepositoryContract {

    fun fetchPopularMovie(page: Int) : Single<PopularMovieResponse>
}