package com.gunwoo.karaoke.data.repository

import com.gunwoo.karaoke.data.datasource.HidingDataSource
import com.gunwoo.karaoke.data.datasource.RecentDataSource
import com.gunwoo.karaoke.data.exception.ListEmptyException
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.domain.repository.RecentRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class RecentRepositoryImpl @Inject constructor(
    private val recentDataSource: RecentDataSource,
    private val hidingDataSource: HidingDataSource
) : RecentRepository {

    override fun getRecentList(): Single<List<YoutubeData>> {
        return recentDataSource.getRecentList().flatMap { recentList ->
            hidingDataSource.getHidingList().flatMap { hidingList ->
                Single.just(getResultRecentList(recentList, hidingList))
            }
        }.map { if (it.isEmpty()) throw ListEmptyException("최근 기록이 없습니다") else it }
    }

    override fun insertRecent(youtubeData: YoutubeData): Completable {
        return recentDataSource.insertRecent(youtubeData)
    }

    private fun getResultRecentList(recentList: List<YoutubeData>, hidingList: List<YoutubeData>): List<YoutubeData> {
        val list = ArrayList<YoutubeData>()

        recentList.map { recent ->
            var isHidden = false

            hidingList.forEach { hiding ->
                if (hiding.videoId == recent.videoId)
                    isHidden = true
            }

            if (!isHidden)
                list.add(recent)
        }

        return list
    }
}