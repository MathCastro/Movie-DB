package com.supercharge.Homework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.supercharge.Homework.controller.MovieController

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MovieController().getMovies(this)
    }
}
