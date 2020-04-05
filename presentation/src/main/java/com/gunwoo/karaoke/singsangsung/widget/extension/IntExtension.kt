package com.gunwoo.karaoke.singsangsung.widget.extension

import java.util.concurrent.TimeUnit

fun Int.millisecondsToMinutes(): String {
    return String.format("%02d:%02d",
        TimeUnit.MILLISECONDS.toMinutes(this.toLong()),
        TimeUnit.MILLISECONDS.toSeconds(this.toLong()) -
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(this.toLong()))
    )
}