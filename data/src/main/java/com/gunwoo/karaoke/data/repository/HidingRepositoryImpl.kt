package com.gunwoo.karaoke.data.repository

import com.gunwoo.karaoke.data.datasource.HidingDataSource
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.domain.repository.HidingRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class HidingRepositoryImpl @Inject constructor(
    private val hidingDataSource: HidingDataSource
) : HidingRepository {

    override fun getHidingList(): Single<List<YoutubeData>> {
        return hidingDataSource.getHidingList().map { if (it.isEmpty()) throw Exception("숨김 리스트가 없습니다") else it }
    }

    override fun insertHiding(youtubeData: YoutubeData): Completable {
        return hidingDataSource.insertHiding(youtubeData)
    }

    override fun deleteHiding(youtubeData: YoutubeData): Completable {
        return hidingDataSource.deleteHiding(youtubeData)
    }
}