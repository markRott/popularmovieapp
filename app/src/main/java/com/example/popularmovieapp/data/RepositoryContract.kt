package com.example.popularmovieapp.data

import com.example.popularmovieapp.entities.ui.MoviesUiData
import io.reactivex.Single

interface RepositoryContract {

    fun fetchPopularMovie(page: Int) : Single<MoviesUiData>
}