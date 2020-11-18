package com.example.popularmovieapp.entities

import com.example.popularmovieapp.entities.dto.MovieDto
import com.example.popularmovieapp.entities.dto.MovieResponseDto
import com.example.popularmovieapp.entities.ui.MovieItem
import com.example.popularmovieapp.entities.ui.MoviesData

fun MovieResponseDto.toDomain(): MoviesData = MoviesData(
    page = page ?: 0,
    totalResults = totalResults ?: 0,
    totalPages = totalPages ?: 0,
    results = results?.toDomain() ?: emptyList()
)

fun MovieDto.toDomain(): MovieItem = MovieItem(
    id = id,
    posterPath = posterPath ?: "",
    title = title ?: "",
    overview = overview ?: ""
)

fun List<MovieDto>.toDomain(): List<MovieItem> {
    return this.map { it.toDomain() }
}