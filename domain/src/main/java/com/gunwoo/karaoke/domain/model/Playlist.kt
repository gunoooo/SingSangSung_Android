package com.gunwoo.karaoke.domain.model

data class Playlist(
    val title: String,
    val thumbnail: String,
    val playlistId: String
) {

    companion object {
        fun getGenrePlaylistList(): List<Playlist> {
            val playlistList = ArrayList<Playlist>()
            playlistList.apply {
                add(
                    Playlist(
                        "발라드",
                        "https://threehillschamber.ca/wp-content/uploads/2016/09/mic4-copy-1-e1515647234824.jpg",
                        ""
                    )
                )
                add(
                    Playlist(
                        "랩/힙합",
                        "https://img1.daumcdn.net/thumb/R720x0.q80/?scode=mtistory2&fname=http%3A%2F%2Fcfile9.uf.tistory.com%2Fimage%2F224B624356DE9B4D190C9A",
                        ""
                    )
                )
                add(
                    Playlist(
                        "KPOP/댄스",
                        "https://static01.nyt.com/images/2020/03/05/arts/04album-bts1/merlin_169984887_ee5e1270-567f-43c3-a536-7175b390b493-superJumbo.jpg",
                        ""
                    )
                )
                add(
                    Playlist(
                        "팝송",
                        "https://t1.daumcdn.net/cfile/tistory/99D26E3E5CE53F6927",
                        ""
                    )
                )
                add(
                    Playlist(
                        "영화/드라마 OST",
                        "https://ilyricsbuzz.com/wp-content/uploads/2020/02/Itaewon-Class-OST-8.jpg",
                        ""
                    )
                )
                add(
                    Playlist(
                        "동요",
                        "https://yt3.ggpht.com/a/AGF-l7-EUhmT01J2oKsezQXDUcyzkfSlbATq4zxjHQ=s900-c-k-c0xffffffff-no-rj-mo",
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
                        "https://youthpress.net/xe/files/attach/images/9794/954/162/25b6a01712e94907618315e02a40d752.PNG",
                        ""
                    )
                )
                add(
                    Playlist(
                        "고등래퍼 2",
                        "https://c-sf.smule.com/rs-s79/arr/86/de/0b1debf7-1b59-41ef-a9bd-7ea2de8c0116_512.jpg",
                        ""
                    )
                )
                add(
                    Playlist(
                        "고등래퍼 3",
                        "https://www.slist.kr/news/photo/201904/75512_156870_5725.jpg",
                        ""
                    )
                )
                add(
                    Playlist(
                        "미스터트롯",
                        "https://t1.daumcdn.net/liveboard/music/8fe343c33f824fc79f4bd5ba9af6d41e.jpg",
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
                        "https://ak4.picdn.net/shutterstock/videos/3055564/thumb/1.jpg",
                        ""
                    )
                )
                add(
                    Playlist(
                        "우울한",
                        "https://img.khan.co.kr/news/2017/09/26/l_2017092701003259200291921.jpg",
                        ""
                    )
                )
                add(
                    Playlist(
                        "잔잔한",
                        "https://cdn.crowdpic.net/list-thumb/thumb_l_EAF00BC0663B9D1960F4CE0203C577C4.jpg",
                        ""
                    )
                )
                add(
                    Playlist(
                        "파워풀한",
                        "https://pbs.twimg.com/profile_images/818323626931818496/Z_y2Fnym_400x400.jpg",
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
                        "고등래퍼 3",
                        "https://www.slist.kr/news/photo/201904/75512_156870_5725.jpg",
                        ""
                    )
                )
                add(
                    Playlist(
                        "KPOP/댄스",
                        "https://static01.nyt.com/images/2020/03/05/arts/04album-bts1/merlin_169984887_ee5e1270-567f-43c3-a536-7175b390b493-superJumbo.jpg",
                        ""
                    )
                )
                add(
                    Playlist(
                        "미스터트롯",
                        "https://t1.daumcdn.net/liveboard/music/8fe343c33f824fc79f4bd5ba9af6d41e.jpg",
                        ""
                    )
                )
                add(
                    Playlist(
                        "신나는",
                        "https://ak4.picdn.net/shutterstock/videos/3055564/thumb/1.jpg",
                        ""
                    )
                )
            }
            return playlistList
        }
    }
}