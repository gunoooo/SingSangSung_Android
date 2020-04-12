package com.gunwoo.karaoke.domain.usecase.favorites

import com.gunwoo.karaoke.domain.base.ParamsUseCase
import com.gunwoo.karaoke.domain.model.Favorites
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.domain.repository.*
import com.gunwoo.karaoke.domain.util.yearDateWeekFormat
import io.reactivex.Completable
import java.util.*
import javax.inject.Inject

class UpdateFavoritesUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) : ParamsUseCase<UpdateFavoritesUseCase.Params, Completable>() {

    override fun buildUseCaseObservable(params: Params): Completable {
        return favoritesRepository.updateFavorites(params.favoritesId, params.favoritesTitle)
    }

    data class Params(
        val favoritesId: Int,
        val favoritesTitle: String
    )
}