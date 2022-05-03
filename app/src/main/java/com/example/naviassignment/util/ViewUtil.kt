package com.example.naviassignment.util

import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan
import android.view.View

fun View.setVisibility(visible: Boolean) {
    if (visible) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}

fun String.getSpannable(s: String): CharSequence? {
    val data = SpannableString(s+this)
    val boldStartSpan = 0
    val boldEndSpan = s.length
    data.setSpan(
        StyleSpan(Typeface.BOLD),
        boldStartSpan,
        boldEndSpan,
        Spanned.SPAN_INCLUSIVE_INCLUSIVE
    )
    return data
}