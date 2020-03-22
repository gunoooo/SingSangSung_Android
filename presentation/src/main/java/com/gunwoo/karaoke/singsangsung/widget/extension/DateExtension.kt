package com.gunwoo.karaoke.singsangsung.widget.extension

import java.text.SimpleDateFormat
import java.util.*

fun Date.minuteFormat(): String {
    val format = SimpleDateFormat("mm:ss", Locale.getDefault())

    return format.format(this)
}