package com.example.popularmovieapp.data

import androidx.paging.PagingData
import com.example.popularmovieapp.entities.ui.MovieUiData
import com.example.popularmovieapp.entities.ui.MoviesUiData
import io.reactivex.Flowable
import io.reactivex.Single

interface MoviesRxRepository {

    fun fetchPopularMovie(): Flowable<PagingData<MovieUiData>>
}