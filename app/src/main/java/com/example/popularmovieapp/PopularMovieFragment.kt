package com.example.popularmovieapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

// @Singleton
//class MainViewModel @ViewModelInject constructor(private val themeContract: ThemeContract) : ViewModel() {
class PopularMovieFragment : Fragment() {

    private val movieVM: PopularMovieVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.frg_popular_movie, container, false)


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        println("7777 movieVM = $movieVM")
    }

    companion object {
        fun newInstance(): Fragment {
            return PopularMovieFragment()
        }
    }
}