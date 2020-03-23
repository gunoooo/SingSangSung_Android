package com.gunwoo.karaoke.data.database.cache

import android.app.Application
import com.gunwoo.karaoke.data.base.BaseCache
import com.gunwoo.karaoke.data.database.entity.DownloadEntity
import com.gunwoo.karaoke.data.database.entity.HidingEntity
import com.gunwoo.karaoke.data.database.entity.RecordEntity
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class HidingCache @Inject constructor(application: Application) : BaseCache(application) {

    private val hidingDao = database.hidingDao()

    fun getHidingList(): Single<List<HidingEntity>> = hidingDao.getHidingList()

    fun insertHiding(hidingEntity: HidingEntity): Completable = hidingDao.insert(hidingEntity)

    fun deleteHiding(hidingEntity: HidingEntity): Completable = hidingDao.delete(hidingEntity)
}