package com.example.popularmovieapp.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.popularmovieapp.entities.ui.MovieItem

class MovieDiffUtil : DiffUtil.ItemCallback<MovieItem>() {

    override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean =
        oldItem == newItem
}