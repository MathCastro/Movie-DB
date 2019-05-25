package com.supercharge.Homework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.supercharge.Homework.controller.MovieController
import com.supercharge.Homework.adapter.MoviesAdapter
import androidx.recyclerview.widget.RecyclerView



class MainActivity : AppCompatActivity() {

    var moviesList: RecyclerView? = null
    val adapter: MoviesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        moviesList = this.findViewById(R.id.movies_list)
        moviesList!!.layoutManager = LinearLayoutManager(this)

        MovieController().getMovies(this, moviesList, adapter)
    }
}
