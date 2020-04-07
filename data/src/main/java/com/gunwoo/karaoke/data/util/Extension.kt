package com.gunwoo.karaoke.data.util

import java.text.SimpleDateFormat
import java.util.*

fun String.trimTitle(): String {
    return this
        .replace("[KY ENTERTAINMENT]", "")
        .replace("[KY 금영노래방]", "")
        .replace("[짱가라오케]", "")
        .replace("[짱가라오케/노래방]", "")
        .replace("[짱가라오케/원키/노래방]", "")
        .replace("[짱가라오케/원키/MR]", "")
        .replace("[짱가라오케/MR]", "")
        .replace("[모플레이]", "")
        .replace("[매직씽아싸노래방]", "")
        .replace("[MAGICSING Karaoke]", "")
        .replace("[노래방]", "")
        .replace("[ZZang KARAOKE]", "")
        .replace("[뮤즈온라인]", "")
        .replace("| MAGICSING", "")
        .replace(" / 싱잇 노래방 Karaoke", "")
        .replace("(INSTRUMENTAL)", "")
        .replace("(Instrumental & Lyrics)", "")
        .replace("/ LaLa Karaoke 노래방", "")
        .replace("/ Karaoke 싱잇 노래방", "")
        .replace("KPOP Karaoke", "")
        .replace("[동요 노래방]", "")
        .replace("/ 노래방", "")
        .trim()
}

fun Date.dateFormat(): String {
    val format = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())

    return format.format(this)
}