package com.example.popularmovieapp.ui.adapter.holders

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.popularmovieapp.utils.IMAGE_URL
import com.example.popularmovieapp.R
import com.example.popularmovieapp.entities.ui.MovieItem
import com.example.popularmovieapp.utils.loadImage

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val ivLogo: AppCompatImageView = itemView.findViewById(R.id.iv_logo)
    private val tvName: AppCompatTextView = itemView.findViewById(R.id.tv_name)
    private val tvDescription: AppCompatTextView = itemView.findViewById(R.id.tv_description)

    fun bind(model: MovieItem) {
        tvName.text = model.title
        tvDescription.text = model.overview
        if(model.posterPath.isNotBlank()) {
            ivLogo.loadImage("$IMAGE_URL${model.posterPath}")
        }else {
            ivLogo.setImageResource(R.drawable.ic_launcher_background)
        }
    }
}