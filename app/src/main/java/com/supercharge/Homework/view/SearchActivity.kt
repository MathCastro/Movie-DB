package com.supercharge.Homework.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.supercharge.Homework.R
import com.supercharge.Homework.util.SharedPref
import com.supercharge.Homework.util.validateText
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        supportActionBar?.hide()
    }

    fun onSearch(view: View? = null) {
        if(validateText(search)) {
            SharedPref.putString(search.text.toString(), this)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
