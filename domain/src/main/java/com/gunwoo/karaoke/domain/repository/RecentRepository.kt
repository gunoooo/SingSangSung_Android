package com.gunwoo.karaoke.domain.repository

import com.gunwoo.karaoke.domain.model.YoutubeData
import io.reactivex.Completable
import io.reactivex.Single

interface RecentRepository {
    fun getRecentList(): Single<List<YoutubeData>>

    fun insertRecent(youtubeData: YoutubeData): Completable
}