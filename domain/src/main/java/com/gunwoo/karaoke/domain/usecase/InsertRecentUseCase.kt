package com.gunwoo.karaoke.domain.usecase

import com.gunwoo.karaoke.domain.base.ParamsUseCase
import com.gunwoo.karaoke.domain.model.Record
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.domain.repository.*
import com.gunwoo.karaoke.domain.util.yearDateWeekFormat
import io.reactivex.Completable
import io.reactivex.Single
import java.io.File
import java.util.*
import javax.inject.Inject

class InsertRecentUseCase @Inject constructor(
    private val recentRepository: RecentRepository
) : ParamsUseCase<InsertRecentUseCase.Params, Completable>() {

    override fun buildUseCaseObservable(params: Params): Completable {
        return recentRepository.insertRecent(params.youtubeData)
    }

    data class Params(
        val youtubeData: YoutubeData
    )
}