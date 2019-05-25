package com.supercharge.Homework.service;

import com.supercharge.Homework.model.response.MoviesResponseBO;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieService {

    @GET("/3/search/movie")
    Call<MoviesResponseBO> getAllMoviews(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("query") String query
    );

    @GET("/3/movie")
    Call<MoviesResponseBO> getMovieById(
            @Query("api_key") String apiKey,
            @Query("language") String language
    );
}
