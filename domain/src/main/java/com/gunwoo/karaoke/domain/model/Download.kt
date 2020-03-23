package com.gunwoo.karaoke.domain.model

import java.io.File
import java.io.Serializable

data class Download(
    val videoId: String,
    val title: String,
    val thumbnail: File?,
    val video: File
) : Serializable