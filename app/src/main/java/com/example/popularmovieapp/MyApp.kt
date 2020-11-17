package com.example.popularmovieapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        //https://image.tmdb.org/t/p/w500/hSpm2mMbkd0hLTRWBz0zolnLAyK.jpg
    }
}