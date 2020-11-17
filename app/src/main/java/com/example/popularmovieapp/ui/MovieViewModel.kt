package com.example.popularmovieapp.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.popularmovieapp.data.RepositoryContract
import io.reactivex.disposables.CompositeDisposable

class MovieViewModel @ViewModelInject constructor(private val repository: RepositoryContract) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun fetchData(page: Int) {
        val disposable = repository
            .fetchPopularMovie(page)
            .subscribe(
                { value ->
                    println("Received: $value")
                },
                { error ->
                    println("Error: $error")
                },
            )
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}