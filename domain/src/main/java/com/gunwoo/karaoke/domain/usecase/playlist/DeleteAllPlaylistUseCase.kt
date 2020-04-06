package com.gunwoo.karaoke.domain.usecase.playlist

import com.gunwoo.karaoke.domain.base.BaseUseCase
import com.gunwoo.karaoke.domain.model.Download
import com.gunwoo.karaoke.domain.repository.DownloadRepository
import com.gunwoo.karaoke.domain.repository.PlaylistRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class DeleteAllPlaylistUseCase @Inject constructor(
    private val playlistRepository: PlaylistRepository
) : BaseUseCase<Completable>() {

    override fun buildUseCaseObservable(): Completable {
        return playlistRepository.deleteAllPlaylist()
    }
}