package com.example.popularmovieapp.thread

import io.reactivex.Scheduler

interface ThreadContract {

    fun ui() : Scheduler

    fun bg() : Scheduler
}