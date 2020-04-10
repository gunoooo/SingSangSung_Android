package com.gunwoo.karaoke.data.datasource

import com.gunwoo.karaoke.data.base.BaseDataSource
import com.gunwoo.karaoke.data.database.cache.RecentCache
import com.gunwoo.karaoke.data.mapper.RecentMapper
import com.gunwoo.karaoke.domain.model.YoutubeData
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class RecentDataSource @Inject constructor(
    override val remote: Any,
    override val cache: RecentCache
): BaseDataSource<Any, RecentCache>() {

    private val recentMapper = RecentMapper()

    fun getRecentList(): Single<List<YoutubeData>> =
        cache.getRecentList()
            .map { recentEntityList -> recentEntityList.sortedByDescending { it.idx } }
            .map { recentList -> recentList.map { recentMapper.mapToModel(it) } }

    fun insertRecent(youtubeData: YoutubeData): Completable = cache.insertRecent(recentMapper.mapToEntity(youtubeData))
}