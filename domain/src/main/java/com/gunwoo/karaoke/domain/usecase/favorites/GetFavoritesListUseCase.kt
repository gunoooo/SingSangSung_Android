package com.gunwoo.karaoke.domain.usecase.favorites

import com.gunwoo.karaoke.domain.base.BaseUseCase
import com.gunwoo.karaoke.domain.model.Favorites
import com.gunwoo.karaoke.domain.repository.FavoritesRepository
import io.reactivex.Single
import javax.inject.Inject

class GetFavoritesListUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) : BaseUseCase<Single<List<Favorites>>>() {

    override fun buildUseCaseObservable(): Single<List<Favorites>> {
        return favoritesRepository.getFavoritesList()
    }
}