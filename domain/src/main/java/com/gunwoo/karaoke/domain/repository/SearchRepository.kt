package com.gunwoo.karaoke.domain.repository

import com.gunwoo.karaoke.domain.model.YoutubeData
import io.reactivex.Single

interface SearchRepository {
    fun getSearchList(search: String): Single<List<YoutubeData>>
}