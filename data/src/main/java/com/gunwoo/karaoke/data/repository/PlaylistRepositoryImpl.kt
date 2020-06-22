package com.gunwoo.karaoke.data.repository

import com.gunwoo.karaoke.data.datasource.HidingDataSource
import com.gunwoo.karaoke.data.datasource.PlaylistDataSource
import com.gunwoo.karaoke.data.exception.ListEmptyException
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.domain.repository.PlaylistRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class PlaylistRepositoryImpl @Inject constructor(
    private val playlistDataSource: PlaylistDataSource,
    private val hidingDataSource: HidingDataSource
) : PlaylistRepository {

    override fun getPlaylistsList(id: String): Single<List<YoutubeData>> {
        return playlistDataSource.getPlaylistsList(id).flatMap { playlistList ->
            hidingDataSource.getHidingList().flatMap { hidingList ->
                Single.just(getResultPlaylistsList(playlistList, hidingList))
            }
        }.map { if (it.isEmpty()) throw ListEmptyException("검색 결과가 없습니다") else it }
    }

    override fun deleteAllPlaylist(): Completable {
        return playlistDataSource.deleteAllPlaylist()
    }

    private fun getResultPlaylistsList(playlistList: List<YoutubeData>, hidingList: List<YoutubeData>): List<YoutubeData> {
        val list = ArrayList<YoutubeData>()

        playlistList.map { playlistItem ->
            var isHidden = false

            hidingList.forEach { hiding ->
                if (hiding.videoId == playlistItem.videoId)
                    isHidden = true
            }

            if (!isHidden)
                list.add(playlistItem)
        }

        return list
    }
}