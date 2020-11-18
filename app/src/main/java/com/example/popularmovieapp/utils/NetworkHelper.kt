package com.example.popularmovieapp.utils

import android.content.Context
import android.net.NetworkInfo
import android.util.Log
import com.example.popularmovieapp.thread.ThreadContract
import com.github.pwittchen.reactivenetwork.library.rx2.Connectivity
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import io.reactivex.disposables.CompositeDisposable

class NetworkHelper(private val context: Context, private val thread: ThreadContract) {

    private var hasInternet: Boolean = true
    private val disposable = CompositeDisposable()

    fun observeNetworkConnectivity(retryAction: () -> Unit) {
        disposable.add(
            ReactiveNetwork
                .observeNetworkConnectivity(context)
                .subscribeOn(thread.bg())
                .observeOn(thread.ui())
                .subscribe(
                    { connectivity -> reactionToChangeState(connectivity, retryAction) },
                    { throwable ->
                        Log.d(APP_TAG, "Observe network connectivity error: ${throwable.message}")
                    }
                )
        )
    }

    fun clearCompositeDisposable() {
        disposable.clear()
        Log.d(APP_TAG, "Stop reactive network helper")
    }

    /**
     * Think about it: NetworkInfo.State.CONNECTED and NetworkInfo.State.DISCONNECTED
     * is deprecated.
     */
    private fun reactionToChangeState(connectivity: Connectivity, retryAction: () -> Unit) {
        when (connectivity.state()) {
            NetworkInfo.State.DISCONNECTED -> {
                hasInternet = false
                Log.d(APP_TAG, "Network state == DISCONNECTED")
            }
            NetworkInfo.State.CONNECTED -> {
                if (!hasInternet) {
                    retryAction.invoke()
                    Log.d(APP_TAG, "Retry load data after connect to internet")
                }
                hasInternet = true
                Log.d(APP_TAG, "Network state == CONNECTED")
            }
        }
    }
}