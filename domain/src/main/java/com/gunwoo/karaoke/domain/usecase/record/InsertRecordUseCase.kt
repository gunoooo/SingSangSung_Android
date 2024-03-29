package com.gunwoo.karaoke.domain.usecase.record

import com.gunwoo.karaoke.domain.base.ParamsUseCase
import com.gunwoo.karaoke.domain.model.Record
import com.gunwoo.karaoke.domain.repository.RecordRepository
import com.gunwoo.karaoke.domain.util.yearDateWeekFormat
import io.reactivex.Completable
import java.io.File
import java.util.*
import javax.inject.Inject

class InsertRecordUseCase @Inject constructor(
    private val recordRepository: RecordRepository
) : ParamsUseCase<InsertRecordUseCase.Params, Completable>() {

    override fun buildUseCaseObservable(params: Params): Completable {
        return recordRepository.insertRecord(
            Record(
                params.title,
                Date().yearDateWeekFormat(),
                params.thumbnail,
                params.file
            )
        )
    }

    data class Params(
        val title: String,
        val thumbnail: String,
        val file: File
    )
}