package com.gunwoo.karaoke.domain.usecase

import com.gunwoo.karaoke.domain.base.BaseUseCase
import com.gunwoo.karaoke.domain.model.Download
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.domain.repository.DownloadRepository
import com.gunwoo.karaoke.domain.repository.FavoritesRepository
import io.reactivex.Single
import javax.inject.Inject

class GetFavoritesListUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) : BaseUseCase<Single<List<YoutubeData>>>() {

    override fun buildUseCaseObservable(): Single<List<YoutubeData>> {
        return favoritesRepository.getFavoritesList()
    }
}