package com.gunwoo.karaoke.domain.repository

import com.gunwoo.karaoke.domain.model.YoutubeData
import io.reactivex.Completable
import io.reactivex.Single

interface HidingRepository {
    fun getHidingList(): Single<List<YoutubeData>>

    fun insertHiding(youtubeData: YoutubeData): Completable

    fun deleteHiding(youtubeData: YoutubeData): Completable
}