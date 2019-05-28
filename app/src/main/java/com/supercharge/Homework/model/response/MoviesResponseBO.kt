package com.supercharge.Homework.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.supercharge.Homework.model.MovieBO

class MoviesResponseBO {
    @SerializedName("page")
    @Expose
    val page: Int = 0

    @SerializedName("total_results")
    @Expose
    val totalResults: Int = 0

    @SerializedName("results")
    @Expose
    val movies: List<MovieBO>? = null

    @SerializedName("total_pages")
    @Expose
    val totalPages: Int = 0

}