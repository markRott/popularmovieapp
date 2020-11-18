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

    fun clearCompositeDisposable() {
        disposable.clear()
        Log.d(APP_TAG, "Clear reactive network helper")
    }

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

    private fun reactionToChangeState(connectivity: Connectivity, retryAction: () -> Unit) {
        when (connectivity.state()) {
            NetworkInfo.State.DISCONNECTED -> {
                hasInternet = false
                Log.d(APP_TAG, "Network state == DISCONNECTED")
            }
            NetworkInfo.State.CONNECTED -> {
                if (!hasInternet) {
                    retryAction.invoke()
                }
                hasInternet = true
                Log.d(APP_TAG, "Network state == CONNECTED")
            }
        }
    }
}