package com.supercharge.Homework.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.supercharge.Homework.R
import android.content.Intent
import android.widget.TextView
import com.supercharge.Homework.controller.MovieController
import kotlinx.android.synthetic.main.activity_detail.*


class DetailActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val intent = intent
        val message = intent.getStringExtra("message")
        print(message)

        MovieController().getMovieById(this, message, poster, movie_title, overview, release_date, average)
    }
}
