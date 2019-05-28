package com.supercharge.Homework.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MovieBO {
    @SerializedName("id")
    @Expose
    val id: Int = 0

    @SerializedName("title")
    @Expose
    val title: String? = null

    @SerializedName("overview")
    @Expose
    val overview: String? = null

    @SerializedName("popularity")
    @Expose
    val popularity: Double = 0.toDouble()

    @SerializedName("backdrop_path")
    @Expose
    val posterPath: String? = null

    @SerializedName("poster_path")
    @Expose
    val picture: String? = null

    @SerializedName("release_date")
    @Expose
    val releaseDate: String? = null

    @SerializedName("vote_average")
    @Expose
    val vote_average: Double = 0.toDouble()

    @SerializedName("genre_ids")
    @Expose
    val genreIds: List<Int>? = null

}