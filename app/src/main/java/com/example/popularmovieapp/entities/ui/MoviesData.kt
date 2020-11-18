package com.example.popularmovieapp.entities.ui

data class MoviesData(
    val page: Int,
    val totalResults: Int,
    val totalPages: Int,
    val results: List<MovieItem>
)