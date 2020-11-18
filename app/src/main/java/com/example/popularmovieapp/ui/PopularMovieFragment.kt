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
import com.example.popularmovieapp.ui.adapter.ErrorStateAdapter
import com.example.popularmovieapp.ui.adapter.MovieAdapter
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@AndroidEntryPoint
class PopularMovieFragment : Fragment() {

    private val movieViewModel: MovieViewModel by viewModels()
    private val movieAdapter: MovieAdapter = MovieAdapter()
    private val disposable = CompositeDisposable()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.frg_popular_movie, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rcvMovie = view.findViewById<RecyclerView>(R.id.rcv_movies)

        //bind the LoadStateAdapter with the photoAdapter
        val mergeAdapter = movieAdapter.withLoadStateFooter(ErrorStateAdapter())
        rcvMovie.adapter = mergeAdapter

        disposable.add(
                movieViewModel
                        .fetchPopularMovie()
                        .subscribe { movieAdapter.submitData(lifecycle, it) })
    }

    override fun onDestroyView() {
        disposable.dispose()
        super.onDestroyView()
    }

    companion object {
        fun newInstance(): Fragment {
            return PopularMovieFragment()
        }
    }
}