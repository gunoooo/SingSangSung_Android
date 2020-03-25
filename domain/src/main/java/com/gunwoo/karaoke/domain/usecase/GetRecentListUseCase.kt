package com.gunwoo.karaoke.domain.usecase

import com.gunwoo.karaoke.domain.base.BaseUseCase
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.domain.repository.RecentRepository
import io.reactivex.Single
import javax.inject.Inject

class GetRecentListUseCase @Inject constructor(
    private val recentRepository: RecentRepository
) : BaseUseCase<Single<List<YoutubeData>>>() {

    override fun buildUseCaseObservable(): Single<List<YoutubeData>> {
        return recentRepository.getRecentList()
    }
}