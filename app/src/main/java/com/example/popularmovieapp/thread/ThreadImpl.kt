package com.example.popularmovieapp.thread

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ThreadImpl : ThreadContract {

    override fun ui(): Scheduler  = AndroidSchedulers.mainThread()

    override fun bg(): Scheduler  = Schedulers.io()
}