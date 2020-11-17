package com.example.popularmovieapp.entities.ui

data class MovieUiData(
    val id: Long,
    val popularity: Double,
    val posterPath: String,
    val backdropPath: String,
    val title: String,
    val overview: String,
    val releaseDate: String
)