package com.example.popularmovieapp.data

import androidx.paging.PagingData
import com.example.popularmovieapp.entities.ui.MovieItem
import io.reactivex.Flowable

interface MoviesRepository {

    fun fetchPopularMovie(): Flowable<PagingData<MovieItem>>
}