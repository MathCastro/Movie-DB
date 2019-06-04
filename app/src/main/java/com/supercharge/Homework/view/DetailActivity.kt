package com.supercharge.Homework.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.supercharge.Homework.R
import android.content.Intent
import android.view.MenuItem
import android.widget.TextView
import com.supercharge.Homework.controller.MovieController
import com.supercharge.Homework.model.MovieBO
import kotlinx.android.synthetic.main.activity_detail.*


class DetailActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = ""

        val intent = intent
        val message = intent.getStringExtra("message")

        MovieController().getMovieById(this, message, poster) {
                movie: MovieBO ->
                    movie_title.text = movie.title
                    overview.text = movie.overview
                    release_date.text = movie.releaseDate!!.replace('-', '/')
                    average.text = movie.voteAverage.toString()
                    movie
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
