package com.supercharge.Homework.model.response;

import android.graphics.Movie;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.supercharge.Homework.model.MovieBO;

import java.util.List;

public class MoviesResponseBO {

    @SerializedName("page")
    @Expose
    private int page;

    @SerializedName("total_results")
    @Expose
    private int totalResults;

    @SerializedName("results")
    @Expose
    private List<MovieBO> movies;

    @SerializedName("total_pages")
    @Expose
    private int totalPages;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<MovieBO> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieBO> movies) {
        this.movies = movies;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
