package com.example.popularmovieapp.ui

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava2.cachedIn
import com.example.popularmovieapp.utils.APP_TAG
import com.example.popularmovieapp.utils.NetworkHelper
import com.example.popularmovieapp.data.MoviesRxRepository
import com.example.popularmovieapp.entities.ui.MovieUiData
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable

class MovieViewModel @ViewModelInject constructor(
    private val repository: MoviesRxRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val disposable = CompositeDisposable()

    private val _movieMLD = MutableLiveData<PagingData<MovieUiData>>()
    val movieLD: LiveData<PagingData<MovieUiData>> get() = _movieMLD

    private val _updateAdapterMLD = MutableLiveData<Boolean>()
    val updateAdapterLD: LiveData<Boolean> get() = _updateAdapterMLD

    init {
        fetchMovies()
    }

    fun startCheckNetworkConnection() {
        networkHelper.observeNetworkConnectivity {
            _updateAdapterMLD.value = true
        }
    }

    fun stopCheckNetworkConnection(){
        networkHelper.clearCompositeDisposable()
    }

    private fun fetchMovies() {
        disposable.add(
            fetchPopularMovie()
                .subscribe(
                    { data -> _movieMLD.value = data },
                    { error -> println(error) }
                )
        )
    }

    private fun fetchPopularMovie(): Flowable<PagingData<MovieUiData>> {
        return repository
            .fetchPopularMovie()
            .cachedIn(viewModelScope)
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(APP_TAG, "Clear MovieViewModel class")
    }
}