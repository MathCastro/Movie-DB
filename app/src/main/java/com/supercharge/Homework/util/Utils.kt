package com.supercharge.Homework.util

import android.content.DialogInterface
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

object Utils {

    @JvmStatic
    fun showDialog(text: String, activity: AppCompatActivity) {

        val dialog = AlertDialog.Builder(activity)
            .setTitle(text)
            .setPositiveButton("Ok", DialogInterface.OnClickListener { _, _ ->
            })
            .create()
        dialog.show()
    }


    private fun isEmpty(editText: TextView): Boolean {

        val input = editText.text.toString().trim { it <= ' ' }
        return input.isEmpty()

    }

    private fun setError(editText: TextView, errorString: String) {

        editText.error = errorString

    }

    private fun clearError(editText: TextView) {

        editText.error = null

    }

    @JvmStatic
    fun validateText(editText: TextView?): Boolean {
        if (editText != null) {
            if (isEmpty(editText)) {
                setError(editText, "Field can not be blank")
            } else {
                clearError(editText)
                return true
            }
        }
        return false
    }
}

