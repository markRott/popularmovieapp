package com.example.popularmovieapp.ui

import android.app.AlertDialog
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.popularmovieapp.APP_TAG
import com.example.popularmovieapp.R
import com.example.popularmovieapp.api.AppApi
import com.example.popularmovieapp.thread.ThreadContract
import com.example.popularmovieapp.ui.adapter.ErrorStateAdapter
import com.example.popularmovieapp.ui.adapter.MovieAdapter
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

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
        movieViewModel.checkNetworkConnection()
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
        movieViewModel.movieLD.observe(viewLifecycleOwner) {
            movieAdapter.submitData(lifecycle, it)
        }
    }

    private fun subscribeToRetryLogic() {
        movieViewModel.updateAdapterLD.observe(viewLifecycleOwner) {
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