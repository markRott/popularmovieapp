package com.example.popularmovieapp.api

import com.example.popularmovieapp.entities.dto.MovieResponseDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface AppApi {

    @GET("/3/movie/popular")
    fun fetchPopularMovie(@Query("page") page: Int): Single<MovieResponseDto>
}