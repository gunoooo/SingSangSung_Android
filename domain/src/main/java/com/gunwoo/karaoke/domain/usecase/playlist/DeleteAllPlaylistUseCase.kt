package com.gunwoo.karaoke.domain.usecase.playlist

import com.gunwoo.karaoke.domain.base.BaseUseCase
import com.gunwoo.karaoke.domain.repository.PlaylistRepository
import io.reactivex.Completable
import javax.inject.Inject

class DeleteAllPlaylistUseCase @Inject constructor(
    private val playlistRepository: PlaylistRepository
) : BaseUseCase<Completable>() {

    override fun buildUseCaseObservable(): Completable {
        return playlistRepository.deleteAllPlaylist()
    }
}