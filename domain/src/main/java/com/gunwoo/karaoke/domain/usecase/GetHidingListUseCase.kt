package com.gunwoo.karaoke.domain.usecase

import com.gunwoo.karaoke.domain.base.BaseUseCase
import com.gunwoo.karaoke.domain.model.Download
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.domain.repository.DownloadRepository
import com.gunwoo.karaoke.domain.repository.FavoritesRepository
import com.gunwoo.karaoke.domain.repository.HidingRepository
import io.reactivex.Single
import javax.inject.Inject

class GetHidingListUseCase @Inject constructor(
    private val hidingRepository: HidingRepository
) : BaseUseCase<Single<List<YoutubeData>>>() {

    override fun buildUseCaseObservable(): Single<List<YoutubeData>> {
        return hidingRepository.getHidingList()
    }
}