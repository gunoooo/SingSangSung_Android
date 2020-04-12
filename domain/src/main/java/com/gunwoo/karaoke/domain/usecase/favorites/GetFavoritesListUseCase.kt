package com.gunwoo.karaoke.domain.usecase.favorites

import com.gunwoo.karaoke.domain.base.ParamsUseCase
import com.gunwoo.karaoke.domain.model.Favorites
import com.gunwoo.karaoke.domain.repository.FavoritesRepository
import io.reactivex.Single
import javax.inject.Inject

class GetFavoritesListUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) : ParamsUseCase<GetFavoritesListUseCase.Params, Single<List<Favorites>>>() {

    override fun buildUseCaseObservable(params: Params): Single<List<Favorites>> {
        return if (params.videoId == null)
            favoritesRepository.getFavoritesList()
        else
            favoritesRepository.getFavoritesList(params.videoId)
    }

    data class Params(
        val videoId: String?
    ) {
        constructor() : this(null)
    }
}