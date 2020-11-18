package com.example.popularmovieapp.entities

import com.example.popularmovieapp.entities.dto.MovieDto
import com.example.popularmovieapp.entities.dto.MovieResponseDto
import com.example.popularmovieapp.entities.ui.MovieUiData
import com.example.popularmovieapp.entities.ui.MoviesUiData

fun MovieResponseDto.toDomain(): MoviesUiData = MoviesUiData(
    page = page ?: 0,
    totalResults = totalResults ?: 0,
    totalPages = totalPages ?: 0,
    results = results?.toDomain() ?: emptyList()
)

fun MovieDto.toDomain(): MovieUiData = MovieUiData(
    id = id,
    posterPath = posterPath ?: "",
    title = title ?: "",
    overview = overview ?: ""
)

fun List<MovieDto>.toDomain(): List<MovieUiData> {
    return this.map { it.toDomain() }
}