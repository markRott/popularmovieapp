package com.example.popularmovieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.popularmovieapp.ui.PopularMovieFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.frm_holder, PopularMovieFragment.newInstance())
                .commit()
        }
    }
}