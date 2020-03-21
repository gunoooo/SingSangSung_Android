package com.gunwoo.karaoke.data.database.cache

import android.app.Application
import com.gunwoo.karaoke.data.base.BaseCache
import com.gunwoo.karaoke.data.database.entity.RecordEntity
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class RecordCache @Inject constructor(application: Application) : BaseCache(application) {

    private val recordDao = database.recordDao()

    fun getRecordList(): Single<List<RecordEntity>> = recordDao.getRecordList()

    fun insertRecord(recordEntity: RecordEntity): Completable = recordDao.insert(recordEntity)
}