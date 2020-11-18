package com.example.popularmovieapp.data

import androidx.paging.PagingData
import com.example.popularmovieapp.entities.ui.MovieUiData
import io.reactivex.Flowable

interface MoviesRxRepository {

    fun fetchPopularMovie(): Flowable<PagingData<MovieUiData>>
}