package com.supercharge.Homework.service

import com.supercharge.Homework.model.MovieBO
import com.supercharge.Homework.model.response.MoviesResponseBO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("/3/search/movie")
    fun getAllMoviews(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("query") query: String
    ): Call<MoviesResponseBO>

    @GET("/3/movie/{movie_id}")
    fun getMovieById(
        @Path(value = "movie_id", encoded = true) movieId: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<MovieBO>
}