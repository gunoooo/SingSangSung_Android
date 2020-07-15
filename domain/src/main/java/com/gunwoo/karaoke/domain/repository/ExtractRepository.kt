package com.gunwoo.karaoke.domain.repository

import io.reactivex.Single

interface ExtractRepository {
    fun extract(videoId: String): Single<String>
}