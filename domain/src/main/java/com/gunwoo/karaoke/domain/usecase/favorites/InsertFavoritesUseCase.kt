package com.gunwoo.karaoke.domain.usecase.favorites

import com.gunwoo.karaoke.domain.base.ParamsUseCase
import com.gunwoo.karaoke.domain.model.Favorites
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.domain.repository.*
import io.reactivex.Completable
import javax.inject.Inject

class InsertFavoritesUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) : ParamsUseCase<InsertFavoritesUseCase.Params, Completable>() {

    override fun buildUseCaseObservable(params: Params): Completable {
        return favoritesRepository.insertFavorites(Favorites(params.favoritesTitle, params.createDate))
    }

    data class Params(
        val favoritesTitle: String,
        val createDate: String
    )
}