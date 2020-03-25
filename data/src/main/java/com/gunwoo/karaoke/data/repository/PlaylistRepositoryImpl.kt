package com.gunwoo.karaoke.data.repository

import com.gunwoo.karaoke.data.datasource.DownloadDataSource
import com.gunwoo.karaoke.data.datasource.FavoritesDataSource
import com.gunwoo.karaoke.data.datasource.HidingDataSource
import com.gunwoo.karaoke.data.datasource.PlaylistDataSource
import com.gunwoo.karaoke.domain.model.Download
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.domain.model.youtuberesponse.playlist.PlaylistItem
import com.gunwoo.karaoke.domain.repository.PlaylistRepository
import io.reactivex.Completable
import io.reactivex.Single
import java.lang.Exception
import javax.inject.Inject

class PlaylistRepositoryImpl @Inject constructor(
    private val playlistDataSource: PlaylistDataSource,
    private val favoritesDataSource: FavoritesDataSource,
    private val hidingDataSource: HidingDataSource,
    private val downloadDataSource: DownloadDataSource
) : PlaylistRepository {

    private lateinit var playlistList: List<YoutubeData>
    private lateinit var favoritesList: List<YoutubeData>
    private lateinit var hidingList: List<YoutubeData>
    private lateinit var downloadList: List<Download>

    override fun getPlaylistsList(id: String): Single<List<YoutubeData>> {
        return playlistDataSource.getPlaylistsList(id).flatMap { playlistList -> this.playlistList = playlistList
            favoritesDataSource.getFavoritesList().flatMap { favoritesList -> this.favoritesList = favoritesList
                hidingDataSource.getHidingList().flatMap { hidingList -> this.hidingList = hidingList
                    downloadDataSource.getDownloadList().flatMap { downloadList -> this.downloadList = downloadList
                        Single.just(getResultPlaylistsList())
                    }
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

        list.forEach { playlistItem ->
            favoritesList.forEach { favorites ->
                @Suppress("LABEL_NAME_CLASH")
                if (playlistItem.videoId == favorites.videoId) {
                    playlistItem.state = YoutubeData.State.FAVORITES

                    downloadList.forEach { download ->
                        if (favorites.videoId == download.videoId) {
                            playlistItem.state = YoutubeData.State.FAVORITES_AND_DOWNLOAD

                            return@forEach
                        }
                    }

                    return@forEach
                }
            }
        }

        return list
    }
}