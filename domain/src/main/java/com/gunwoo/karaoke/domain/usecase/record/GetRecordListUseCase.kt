package com.gunwoo.karaoke.domain.usecase.record

import com.gunwoo.karaoke.domain.base.BaseUseCase
import com.gunwoo.karaoke.domain.base.ParamsUseCase
import com.gunwoo.karaoke.domain.model.Record
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.domain.repository.PlaylistRepository
import com.gunwoo.karaoke.domain.repository.RecordRepository
import com.gunwoo.karaoke.domain.repository.SearchRepository
import io.reactivex.Single
import javax.inject.Inject

class GetRecordListUseCase @Inject constructor(
    private val recordRepository: RecordRepository
) : BaseUseCase<Single<List<Record>>>() {

    override fun buildUseCaseObservable(): Single<List<Record>> {
        return recordRepository.getRecordList()
    }
}