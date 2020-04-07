package com.gunwoo.karaoke.data.database.cache

import android.app.Application
import com.gunwoo.karaoke.data.base.BaseCache
import com.gunwoo.karaoke.data.database.entity.RecentEntity
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class RecentCache @Inject constructor(application: Application) : BaseCache(application) {

    private val recentDao = database.recentDao()

    fun getRecentList(): Single<List<RecentEntity>> = recentDao.getRecentList()

    fun insertRecent(recentEntity: RecentEntity): Completable = recentDao.insert(recentEntity)
}