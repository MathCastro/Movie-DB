package com.supercharge.Homework.controller

import android.content.Intent
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import com.supercharge.Homework.R
import com.supercharge.Homework.adapter.MoviesAdapter
import com.supercharge.Homework.model.MovieBO
import com.supercharge.Homework.model.response.MoviesResponseBO
import com.supercharge.Homework.service.MovieService
import com.supercharge.Homework.util.Utils
import com.supercharge.Homework.view.DetailActivity
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieController {

    private val language = "en_US"

    fun getMovies(activity: AppCompatActivity, moviesList: RecyclerView, search: String) {

        val baseUrl = activity.getResources().getString(R.string.base_url)
        val apiKey = activity.getResources().getString(R.string.api_key)

        val httpClient = OkHttpClient.Builder()

        val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(baseUrl)
            .client(httpClient.build()).build()

        val movieService = retrofit.create(MovieService::class.java)

        val call = movieService.getAllMoviews(apiKey, language, search)

        call.enqueue(object : Callback<MoviesResponseBO> {
            override fun onResponse(call: Call<MoviesResponseBO>, response: retrofit2.Response<MoviesResponseBO>) {
                if (response.isSuccessful) {
                    println(response.body()!!.movies)
                    println("TAG" + "response 33: " + Gson().toJson(response.body()!!.movies))

                    if (response.body()!!.movies!!.isEmpty()) {
                        Utils.showDialog("Did not found any movie", activity)
                    }
                    moviesList.adapter = MoviesAdapter(
                        response.body()!!.movies!!,
                        object : MoviesAdapter.OnItemClickListener {
                            override fun onItemClick(item: MovieBO) {
                                val myIntent = Intent(activity, DetailActivity::class.java)
                                myIntent.putExtra("message", item.id.toString())
                                activity.startActivity(myIntent)
                            }
                        })

                } else {
                    when {
                        response.code() == 400 -> Utils.showDialog("Not found any movie", activity)
                        response.code() == 401 -> Utils.showDialog("Invalid API key", activity)
                        else -> Utils.showDialog("Unexpected error", activity)
                    }

                }

            }

            override fun onFailure(call: Call<MoviesResponseBO>, t: Throwable) {
                Utils.showDialog("Could not connect to server", activity)
            }
        })
    }

    fun getMovieById(
        activity: AppCompatActivity,
        id: String,
        poster: ImageView,
        title: TextView,
        overview: TextView,
        releaseDate: TextView,
        average: TextView
    ) {

        val baseUrl = activity.resources.getString(R.string.base_url)
        val apiKey = activity.resources.getString(R.string.api_key)

        val httpClient = OkHttpClient.Builder()

        val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(baseUrl)
            .client(httpClient.build()).build()

        val movieService = retrofit.create(MovieService::class.java)

        val call = movieService.getMovieById(id, apiKey, language)

        call.enqueue(object : Callback<MovieBO> {
            override fun onResponse(call: Call<MovieBO>, response: retrofit2.Response<MovieBO>) {
                if (response.isSuccessful) {
                    println(response.body())
                    println("TAG" + "response 33: " + Gson().toJson(response.body()))
                    val url = "http://image.tmdb.org/t/p/w500" + response.body()!!.picture!!
                    Glide.with(poster)
                        .load(url)
                        .apply(RequestOptions.skipMemoryCacheOf(true))
                        .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                        .into(poster)
                    title.text = response.body()!!.title
                    overview.text = response.body()!!.overview
                    releaseDate.text = response.body()!!.releaseDate!!.replace('-', '/')
                    average.text = response.body()!!.vote_average.toString()

                } else {
                    if (response.code() == 400) {
                        Utils.showDialog("Not found any movie", activity)
                    } else if (response.code() == 401) {
                        Utils.showDialog("Invalid API key", activity)
                    } else {
                        Utils.showDialog("Unexpected error", activity)
                    }

                }

            }

            override fun onFailure(call: Call<MovieBO>, t: Throwable) {
                Utils.showDialog("Could not connect to server", activity)
            }
        })
    }
}