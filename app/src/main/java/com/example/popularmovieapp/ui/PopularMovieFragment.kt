package com.example.popularmovieapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.popularmovieapp.R
import com.example.popularmovieapp.api.AppApi
import com.example.popularmovieapp.thread.ThreadContract
import com.example.popularmovieapp.ui.adapter.MovieAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PopularMovieFragment : Fragment() {

    private val movieViewModel: MovieViewModel by viewModels()
    private val movieAdapter: MovieAdapter = MovieAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.frg_popular_movie, container, false)


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        movieViewModel.fetchData(1)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rcvMovie = view.findViewById<RecyclerView>(R.id.rcv_movies)
//        rcvMovie.adapter = movieAdapter
    }

    companion object {
        fun newInstance(): Fragment {
            return PopularMovieFragment()
        }
    }
}