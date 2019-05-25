package com.supercharge.Homework.service;

import com.supercharge.Homework.model.MovieBO;
import com.supercharge.Homework.model.response.MoviesResponseBO;
import retrofit2.Call;
import retrofit2.http.*;

public interface MovieService {

    @GET("/3/search/movie")
    Call<MoviesResponseBO> getAllMoviews(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("query") String query
    );

    @GET("/3/movie/{movie_id}")
    Call<MovieBO> getMovieById(
            @Path(value = "movie_id", encoded = true) String movieId,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );
}
