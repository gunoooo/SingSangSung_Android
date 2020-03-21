package com.gunwoo.karaoke.data.util

fun String.trimTitle(): String {
    return this
        .replace("[KY ENTERTAINMENT]", "")
        .replace("[KY 금영노래방]", "")
        .replace("[짱가라오케]", "")
        .replace("[짱가라오케/노래방]", "")
        .replace("[짱가라오케/원키/노래방]", "")
        .replace("[짱가라오케/원키/MR]", "")
        .replace("[짱가라오케/MR]", "")
        .replace("[매직씽아싸노래방]", "")
        .replace("[MAGICSING Karaoke]", "")
        .replace("[노래방]", "")
        .replace("[ZZang KARAOKE]", "")
        .replace("[뮤즈온라인]", "")
        .replace("| MAGICSING", "")
        .replace("(INSTRUMENTAL)", "")
        .replace("(Instrumental & Lyrics)", "")
        .replace("/ Karaoke 싱잇 노래방", "")
        .replace("KPOP Karaoke", "")
        .trim()
}