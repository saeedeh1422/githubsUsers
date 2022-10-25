package com.itoll.githubusers.common

import android.app.Activity
import android.view.inputmethod.InputMethodManager

fun Activity.hideKeyboard() {
    val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as? InputMethodManager
    currentFocus?.also { imm?.hideSoftInputFromWindow(it.windowToken, 0) }
}

fun Activity.showKeyboard() {
    val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as? InputMethodManager
    currentFocus?.also { imm?.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0) }
}


fun String.toUserName() = "@$this"

fun Long.convertToK(): String {
    return if (this > 999)
        "${this % 1000}K"
    else
        "${this}k"
}