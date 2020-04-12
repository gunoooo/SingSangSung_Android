package com.gunwoo.karaoke.domain.usecase.favorites

import com.gunwoo.karaoke.domain.base.ParamsUseCase
import com.gunwoo.karaoke.domain.model.Favorites
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.domain.repository.*
import com.gunwoo.karaoke.domain.util.yearDateWeekFormat
import io.reactivex.Completable
import java.util.*
import javax.inject.Inject

class InsertFavoritesUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) : ParamsUseCase<InsertFavoritesUseCase.Params, Completable>() {

    override fun buildUseCaseObservable(params: Params): Completable {
        return favoritesRepository.insertFavorites(Favorites(params.title, Date().yearDateWeekFormat()))
    }

    data class Params(
        val title: String
    )
}