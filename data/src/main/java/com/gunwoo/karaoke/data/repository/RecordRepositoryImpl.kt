package com.gunwoo.karaoke.data.repository

import com.gunwoo.karaoke.data.datasource.RecordDataSource
import com.gunwoo.karaoke.data.mapper.RecordMapper
import com.gunwoo.karaoke.domain.model.Record
import com.gunwoo.karaoke.domain.repository.RecordRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class RecordRepositoryImpl @Inject constructor(
    private val recordDataSource: RecordDataSource
) : RecordRepository {

    private val recordMapper = RecordMapper()

    override fun getRecordList(): Single<List<Record>> {
        return recordDataSource.getRecordList().map { recordEntityList -> recordEntityList.map { recordMapper.mapToModel(it) }.filter { it.file.isFile } }
    }

    override fun insertRecord(record: Record): Completable {
        return recordDataSource.insertRecord(recordMapper.mapToEntity(record))
    }
}