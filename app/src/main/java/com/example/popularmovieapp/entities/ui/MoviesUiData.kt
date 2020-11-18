package com.example.popularmovieapp.entities.ui

data class MoviesUiData(
    val page: Int,
    val totalResults: Int,
    val totalPages: Int,
    val results: List<MovieUiData>
)