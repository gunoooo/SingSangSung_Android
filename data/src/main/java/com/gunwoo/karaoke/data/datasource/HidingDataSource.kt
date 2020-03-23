package com.gunwoo.karaoke.data.datasource

import com.gunwoo.karaoke.data.base.BaseDataSource
import com.gunwoo.karaoke.data.database.cache.HidingCache
import com.gunwoo.karaoke.data.database.entity.HidingEntity
import com.gunwoo.karaoke.data.mapper.HidingMapper
import com.gunwoo.karaoke.data.network.remote.PlaylistRemote
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.domain.model.youtuberesponse.playlist.PlaylistItem
import io.reactivex.Completable
import io.reactivex.Single
import java.lang.Exception
import javax.inject.Inject

class HidingDataSource @Inject constructor(
    override val remote: Any,
    override val cache: HidingCache
): BaseDataSource<Any, HidingCache>() {

    private val hidingMapper = HidingMapper()

    fun getHidingList(): Single<List<YoutubeData>> = cache.getHidingList().map { hidingEntityList -> hidingEntityList.map { hidingMapper.mapToModel(it) } }

    fun insertHiding(youtubeData: YoutubeData): Completable = cache.insertHiding(hidingMapper.mapToEntity(youtubeData))

    fun deleteHiding(youtubeData: YoutubeData): Completable = cache.deleteHiding(hidingMapper.mapToEntity(youtubeData))
}