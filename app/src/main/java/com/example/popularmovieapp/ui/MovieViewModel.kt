package com.example.popularmovieapp.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava2.cachedIn
import com.example.popularmovieapp.data.MoviesRxRepository
import com.example.popularmovieapp.entities.ui.MovieUiData
import io.reactivex.Flowable

class MovieViewModel @ViewModelInject constructor(private val repository: MoviesRxRepository) : ViewModel() {

    fun fetchPopularMovie(): Flowable<PagingData<MovieUiData>> {
        return repository
                .fetchPopularMovie()
                .map { pagingData ->
                    println("7777 pagingData: $pagingData")
                    pagingData
                }
                .cachedIn(viewModelScope)
    }
}