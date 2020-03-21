package com.gunwoo.karaoke.domain.repository

import com.gunwoo.karaoke.domain.model.Record
import io.reactivex.Completable
import io.reactivex.Single

interface RecordRepository {
    fun getRecordList(): Single<List<Record>>

    fun insertRecord(record: Record): Completable
}