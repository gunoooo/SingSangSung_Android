package com.gunwoo.karaoke.domain.model

data class Character(
    val name: String,
    val profileImage: String,
    val playlistId: String
) {

    companion object {
        fun getCharacterList(): List<Character> {
            val characterList = ArrayList<Character>()
            characterList.apply {
                add(
                    Character(
                        "아이즈원",
                        "https://search.pstatic.net/common?type=a&size=225x150&quality=95&direct=true&src=http%3A%2F%2Fsstatic.naver.net%2Fpeople%2FportraitGroup%2F201911%2F20191104185140710.jpg",
                        ""
                    )
                )
                add(
                    Character(
                        "아이유",
                        "https://search.pstatic.net/common?type=a&size=120x150&quality=95&direct=true&src=http%3A%2F%2Fsstatic.naver.net%2Fpeople%2Fportrait%2F201911%2F20191119160822583-3294252.jpg",
                        ""
                    )
                )
                add(
                    Character(
                        "지코",
                        "https://search.pstatic.net/common?type=a&size=120x150&quality=95&direct=true&src=http%3A%2F%2Fsstatic.naver.net%2Fpeople%2Fportrait%2F202001%2F20200110110026634-4634995.jpg",
                        ""
                    )
                )
                add(
                    Character(
                        "방탄소년단",
                        "https://search.pstatic.net/common?type=a&size=225x150&quality=95&direct=true&src=http%3A%2F%2Fsstatic.naver.net%2Fpeople%2FportraitGroup%2F202002%2F20200213001842528.jpg",
                        ""
                    )
                )
                add(
                    Character(
                        "창모",
                        "https://search.pstatic.net/common?type=a&size=120x150&quality=95&direct=true&src=http%3A%2F%2Fsstatic.naver.net%2Fpeople%2Fportrait%2F201802%2F20180222120907556.jpg",
                        ""
                    )
                )
                add(
                    Character(
                        "임창정",
                        "https://search.pstatic.net/common?type=a&size=120x150&quality=95&direct=true&src=http%3A%2F%2Fsstatic.naver.net%2Fpeople%2Fportrait%2F201809%2F20180927122958194.jpg",
                        ""
                    )
                )
            }
            return characterList
        }
    }
}