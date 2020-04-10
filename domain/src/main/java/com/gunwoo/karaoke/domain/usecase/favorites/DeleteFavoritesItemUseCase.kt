package com.gunwoo.karaoke.domain.usecase.favorites

import com.gunwoo.karaoke.domain.base.ParamsUseCase
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.domain.repository.*
import io.reactivex.Completable
import javax.inject.Inject

class DeleteFavoritesItemUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) : ParamsUseCase<DeleteFavoritesItemUseCase.Params, Completable>() {

    override fun buildUseCaseObservable(params: Params): Completable {
        return favoritesRepository.deleteFavoritesItem(params.youtubeData, params.favoritesId)
    }

    data class Params(
        val youtubeData: YoutubeData,
        val favoritesId: Int
    )
}