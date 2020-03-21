package com.gunwoo.karaoke.data.datasource

import com.gunwoo.karaoke.data.base.BaseDataSource
import com.gunwoo.karaoke.data.database.cache.RecordCache
import com.gunwoo.karaoke.data.database.entity.RecordEntity
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class RecordDataSource @Inject constructor(
    override val remote: Any,
    override val cache: RecordCache
) : BaseDataSource<Any, RecordCache>() {

    fun getRecordList(): Single<List<RecordEntity>> = cache.getRecordList().map { recordEntityList -> recordEntityList.sortedByDescending { it.idx } }

    fun insertRecord(recordEntity: RecordEntity): Completable = cache.insertRecord(recordEntity)
}