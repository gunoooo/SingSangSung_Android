package com.gunwoo.karaoke.data.util

import com.gunwoo.karaoke.data.R
import com.gunwoo.karaoke.domain.model.Playlist

object PlaylistFactory {

    fun getGenrePlaylistList(): List<Playlist> {
        val playlistList = ArrayList<Playlist>()
        playlistList.apply {
            add(
                Playlist(
                    "발라드",
                    R.drawable.thumbnail_ballade,
                    ""
                )
            )
            add(
                Playlist(
                    "랩/힙합",
                    R.drawable.thumbnail_hiphop,
                    ""
                )
            )
            add(
                Playlist(
                    "KPOP/댄스",
                    R.drawable.thumbnail_kpop,
                    ""
                )
            )
            add(
                Playlist(
                    "팝송",
                    R.drawable.thumbnail_pop,
                    ""
                )
            )
            add(
                Playlist(
                    "영화/드라마 OST",
                    R.drawable.thumbnail_ost,
                    ""
                )
            )
            add(
                Playlist(
                    "동요",
                    R.drawable.thumbnail_child,
                    ""
                )
            )
        }
        return playlistList
    }

    fun getProgramPlaylistList(): List<Playlist> {
        val playlistList = ArrayList<Playlist>()
        playlistList.apply {
            add(
                Playlist(
                    "고등래퍼 1",
                    R.drawable.thumbnail_default_1,
                    ""
                )
            )
            add(
                Playlist(
                    "고등래퍼 2",
                    R.drawable.thumbnail_default_2,
                    ""
                )
            )
            add(
                Playlist(
                    "고등래퍼 3",
                    R.drawable.thumbnail_default_3,
                    ""
                )
            )
            add(
                Playlist(
                    "미스터트롯",
                    R.drawable.thumbnail_default_4,
                    ""
                )
            )
        }
        return playlistList
    }

    fun getMoodPlaylistList(): List<Playlist> {
        val playlistList = ArrayList<Playlist>()
        playlistList.apply {
            add(
                Playlist(
                    "신나는",
                    R.drawable.thumbnail_exciting,
                    ""
                )
            )
            add(
                Playlist(
                    "우울한",
                    R.drawable.thumbnail_depressed,
                    ""
                )
            )
            add(
                Playlist(
                    "잔잔한",
                    R.drawable.thumbnail_windless,
                    ""
                )
            )
            add(
                Playlist(
                    "파워풀한",
                    R.drawable.thumbnail_powerful,
                    ""
                )
            )
        }
        return playlistList
    }

    fun getRecommendPlaylistList(): List<Playlist> {
        val playlistList = ArrayList<Playlist>()
        playlistList.apply {
            add(
                Playlist(
                    "잔잔한",
                    R.drawable.thumbnail_windless,
                    ""
                )
            )
            add(
                Playlist(
                    "KPOP/댄스",
                    R.drawable.thumbnail_kpop,
                    ""
                )
            )
            add(
                Playlist(
                    "발라드",
                    R.drawable.thumbnail_ballade,
                    ""
                )
            )
            add(
                Playlist(
                    "신나는",
                    R.drawable.thumbnail_exciting,
                    ""
                )
            )
        }
        return playlistList
    }
}