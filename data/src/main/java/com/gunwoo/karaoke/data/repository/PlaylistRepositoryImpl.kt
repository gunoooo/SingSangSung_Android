package com.gunwoo.karaoke.data.repository

import com.gunwoo.karaoke.data.datasource.FavoritesItemDataSource
import com.gunwoo.karaoke.data.datasource.HidingDataSource
import com.gunwoo.karaoke.data.datasource.PlaylistDataSource
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.domain.repository.PlaylistRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class PlaylistRepositoryImpl @Inject constructor(
    private val playlistDataSource: PlaylistDataSource,
    private val favoritesItemDataSource: FavoritesItemDataSource,
    private val hidingDataSource: HidingDataSource
) : PlaylistRepository {

    private lateinit var playlistList: List<YoutubeData>
    private lateinit var favoritesItemList: List<YoutubeData>
    private lateinit var hidingList: List<YoutubeData>

    override fun getPlaylistsList(id: String): Single<List<YoutubeData>> {
        return playlistDataSource.getPlaylistsList(id).flatMap { playlistList -> this.playlistList = playlistList
            favoritesItemDataSource.getFavoritesItemList().flatMap { favoritesItemList -> this.favoritesItemList = favoritesItemList
                hidingDataSource.getHidingList().flatMap { hidingList -> this.hidingList = hidingList
                    Single.just(getResultPlaylistsList())
                }
            }
        }.map { if (it.isEmpty()) throw Exception("검색 결과가 없습니다") else it }
    }

    override fun deleteAllPlaylist(): Completable {
        return playlistDataSource.deleteAllPlaylist()
    }

    private fun getResultPlaylistsList(): List<YoutubeData> {
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