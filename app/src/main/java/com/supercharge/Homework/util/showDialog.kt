package com.supercharge.Homework.util

import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

fun showDialog(text: String, activity: AppCompatActivity) {

    val dialog = AlertDialog.Builder(activity)
        .setTitle(text)
        .setPositiveButton("Ok", DialogInterface.OnClickListener { dialog, which ->
        })
        .create()
    dialog.show()
}