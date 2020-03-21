package com.gunwoo.karaoke.domain.util

import java.text.SimpleDateFormat
import java.util.*

fun Date.yearDateWeekFormat(): String {
    val format = SimpleDateFormat("yyyy-MM-dd (E)", Locale.getDefault())

    return format.format(this)
}