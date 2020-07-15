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
                    "PLt1UQ3o9-dDDs2HGBM0HmKNbmOEt2TLe6"
                )
            )
            add(
                Playlist(
                    "랩/힙합",
                    R.drawable.thumbnail_hiphop,
                    "PLt1UQ3o9-dDDrvLSKlbZx7t2KABXiwnKF"
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
                    "PLXHRN3PjVAFz40f57CuHk8aVKWVNt3g2g"
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
                    "PLt1UQ3o9-dDAZOchooCfAgnhVVJXSgyix"
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
                    "복면가왕",
                    R.drawable.thumbnail_default_1,
                    "PLXHRN3PjVAFyfbluvu_Ks-mqep8ZoYp9d"
                )
            )
            add(
                Playlist(
                    "쇼미더머니",
                    R.drawable.thumbnail_default_2,
                    "PLXHRN3PjVAFzO87TX9bQI52I9HgU5Dkxx"
                )
            )
            add(
                Playlist(
                    "불후의 명곡",
                    R.drawable.thumbnail_default_3,
                    "PLXHRN3PjVAFyroDAVeWjVWqnXBYGuQJD8"
                )
            )
            add(
                Playlist(
                    "미스터트롯",
                    R.drawable.thumbnail_default_4,
                    "PLXHRN3PjVAFxfhbBjwrRHsdu5bK1Q56Ea"
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
                    "PLt1UQ3o9-dDDs2HGBM0HmKNbmOEt2TLe6"
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