package com.example.popularmovieapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.popularmovieapp.R
import com.example.popularmovieapp.api.AppApi
import com.example.popularmovieapp.thread.ThreadContract
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PopularMovieFragment : Fragment() {

    private val movieViewModel: MovieViewModel by viewModels()
    @Inject lateinit var thread: ThreadContract
    @Inject lateinit var api: AppApi

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.frg_popular_movie, container, false)


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        movieViewModel.fetchData(1)
    }

    companion object {
        fun newInstance(): Fragment {
            return PopularMovieFragment()
        }
    }
}