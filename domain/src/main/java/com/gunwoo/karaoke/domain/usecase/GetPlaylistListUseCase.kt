package com.gunwoo.karaoke.domain.usecase

import com.gunwoo.karaoke.domain.base.ParamsUseCase
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.domain.repository.PlaylistRepository
import com.gunwoo.karaoke.domain.repository.SearchRepository
import io.reactivex.Single
import javax.inject.Inject

class GetPlaylistListUseCase @Inject constructor(
    private val playlistRepository: PlaylistRepository
) : ParamsUseCase<GetPlaylistListUseCase.Params, Single<List<YoutubeData>>>() {

    override fun buildUseCaseObservable(params: Params): Single<List<YoutubeData>> {
        return playlistRepository.getPlaylistsList(params.id)
    }

    data class Params(val id: String)
}