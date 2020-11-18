package com.example.popularmovieapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.popularmovieapp.utils.APP_TAG
import com.example.popularmovieapp.R
import com.example.popularmovieapp.ui.adapter.ErrorStateAdapter
import com.example.popularmovieapp.ui.adapter.MovieAdapter
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable

@AndroidEntryPoint
class PopularMovieFragment : Fragment() {

    private val movieViewModel: MovieViewModel by viewModels()
    private val disposable = CompositeDisposable()

    private val movieAdapter: MovieAdapter = MovieAdapter()
    private val footerErrorAdapter: ErrorStateAdapter = ErrorStateAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = inflater.inflate(R.layout.frg_popular_movie, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView(view)
        subscribeToMovieData()
        subscribeToRetryLogic()
    }

    override fun onResume() {
        super.onResume()
        movieViewModel.startCheckNetworkConnection()
    }

    override fun onPause() {
        super.onPause()
        movieViewModel.stopCheckNetworkConnection()
    }

    private fun initRecyclerView(view: View) {
        val rcvMovie = view.findViewById<RecyclerView>(R.id.rcv_movies)
        val resultAdapter = movieAdapter.withLoadStateFooter(footerErrorAdapter)
        rcvMovie.adapter = resultAdapter

        movieAdapter.addLoadStateListener { loadState ->
            val errorState = loadState.source.refresh as? LoadState.Error
            errorState?.let { footerErrorAdapter.loadState = errorState }
        }
    }

    private fun subscribeToMovieData() {
        movieViewModel.moviesLD.observe(viewLifecycleOwner) {
            movieAdapter.submitData(lifecycle, it)
        }
    }

    private fun subscribeToRetryLogic() {
        movieViewModel.retryLD.observe(viewLifecycleOwner) {
            movieAdapter.retry()
            Log.d(APP_TAG, "Retry any failed load requests")
        }
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