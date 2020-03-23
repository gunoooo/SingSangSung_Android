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

class InsertFavoritesUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) : ParamsUseCase<InsertFavoritesUseCase.Params, Completable>() {

    override fun buildUseCaseObservable(params: Params): Completable {
        return favoritesRepository.insertFavorites(params.youtubeData)
    }

    data class Params(
        val youtubeData: YoutubeData
    )
}