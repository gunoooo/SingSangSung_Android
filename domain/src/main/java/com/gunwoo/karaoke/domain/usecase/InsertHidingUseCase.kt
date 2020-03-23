package com.gunwoo.karaoke.domain.usecase

import com.gunwoo.karaoke.domain.base.ParamsUseCase
import com.gunwoo.karaoke.domain.model.Record
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.domain.repository.HidingRepository
import com.gunwoo.karaoke.domain.repository.PlaylistRepository
import com.gunwoo.karaoke.domain.repository.RecordRepository
import com.gunwoo.karaoke.domain.repository.SearchRepository
import com.gunwoo.karaoke.domain.util.yearDateWeekFormat
import io.reactivex.Completable
import io.reactivex.Single
import java.io.File
import java.util.*
import javax.inject.Inject

class InsertHidingUseCase @Inject constructor(
    private val hidingRepository: HidingRepository
) : ParamsUseCase<InsertHidingUseCase.Params, Completable>() {

    override fun buildUseCaseObservable(params: Params): Completable {
        return hidingRepository.insertHiding(params.youtubeData)
    }

    data class Params(
        val youtubeData: YoutubeData
    )
}