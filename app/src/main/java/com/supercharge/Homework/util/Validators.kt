package com.supercharge.Homework.util

import android.widget.TextView

fun isEmpty(editText: TextView): Boolean {

    val input = editText.text.toString().trim { it <= ' ' }
    return input.length == 0

}

fun setError(editText: TextView, errorString: String) {

    editText.error = errorString

}

fun clearError(editText: TextView) {

    editText.error = null

}

fun validateText(editText: TextView?): Boolean {
    if(editText == null) {
        setError(editText!!, "Field can not be blank")
    } else {
        if(isEmpty(editText!!)) {
            setError(editText!!, "Field can not be blank")
        } else {
            clearError(editText!!)
            return true
        }
    }
    return false
}