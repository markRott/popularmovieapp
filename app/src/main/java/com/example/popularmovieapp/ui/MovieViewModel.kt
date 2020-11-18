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
import com.example.popularmovieapp.data.MoviesRepository
import com.example.popularmovieapp.entities.ui.MovieItem
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable

class MovieViewModel @ViewModelInject constructor(
    private val repository: MoviesRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val disposable = CompositeDisposable()

    private val _moviesMLD = MutableLiveData<PagingData<MovieItem>>()
    val moviesLD: LiveData<PagingData<MovieItem>> get() = _moviesMLD

    private val _retryMLD = MutableLiveData<Boolean>()
    val retryLD: LiveData<Boolean> get() = _retryMLD

    init {
        fetchPopularMovie()
    }

    fun startCheckNetworkConnection() {
        networkHelper.observeNetworkConnectivity {
            _retryMLD.value = true
        }
    }

    fun stopCheckNetworkConnection(){
        networkHelper.clearCompositeDisposable()
    }

    private fun fetchPopularMovie() {
        disposable.add(
            fetch()
                .subscribe(
                    { data -> _moviesMLD.value = data },
                    { error -> println(error) }
                )
        )
    }

    private fun fetch(): Flowable<PagingData<MovieItem>> {
        return repository
            .fetchPopularMovie()
            .cachedIn(viewModelScope)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
        Log.d(APP_TAG, "Clear MovieViewModel class")
    }
}