package com.example.popularmovieapp.ui.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.popularmovieapp.R
import com.example.popularmovieapp.entities.ui.MovieUiData
import com.example.popularmovieapp.utils.inflate

class MovieAdapter : PagingDataAdapter<MovieUiData, MovieViewHolder>(MovieDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(parent.inflate(R.layout.item_movie))

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }
}