package com.supercharge.Homework.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.supercharge.Homework.controller.MovieController
import com.supercharge.Homework.adapter.MoviesAdapter
import androidx.recyclerview.widget.RecyclerView
import com.supercharge.Homework.R
import com.supercharge.Homework.util.SharedPref
import androidx.core.app.NavUtils
import android.view.MenuItem


class MainActivity : AppCompatActivity() {

    var moviesList: RecyclerView? = null
    val adapter: MoviesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = ""

        val search = SharedPref.getString(this)

        moviesList = this.findViewById(R.id.movies_list)
        moviesList!!.layoutManager = LinearLayoutManager(this)

        MovieController().getMovies(this, moviesList, adapter, search)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


}
