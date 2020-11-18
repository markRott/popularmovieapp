package com.example.popularmovieapp.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.popularmovieapp.entities.ui.MovieUiData

class MovieDiffUtil : DiffUtil.ItemCallback<MovieUiData>() {

    override fun areItemsTheSame(oldItem: MovieUiData, newItem: MovieUiData): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: MovieUiData, newItem: MovieUiData): Boolean =
        oldItem == newItem
}