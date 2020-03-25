package com.gunwoo.karaoke.data.util

import com.gunwoo.karaoke.data.R
import com.gunwoo.karaoke.domain.model.Character

object CharacterFactory {

    fun getCharacterList(): List<Character> {
        val characterList = ArrayList<Character>()
        characterList.apply {
            add(
                Character(
                    "아이즈원",
                    R.drawable.profile_izone,
                    "PLjGGC556n3RiR2J9Gnu3iSI-R98eaHMq6"
                )
            )
            add(
                Character(
                    "아이유",
                    R.drawable.profile_iu,
                    "PLt1UQ3o9-dDCLCfEQhf8wJ-weTcIzPerD"
                )
            )
            add(
                Character(
                    "지코",
                    R.drawable.profile_zico,
                    "PLjGGC556n3RhUazcXcebIuJGgckQ4UaUu"
                )
            )
            add(
                Character(
                    "방탄소년단",
                    R.drawable.profile_bts,
                    "PLt1UQ3o9-dDB_Owj-TiezBFvVVqsm8YEN"
                )
            )
            add(
                Character(
                    "창모",
                    R.drawable.profile_changmo,
                    ""
                )
            )
            add(
                Character(
                    "임창정",
                    R.drawable.profile_changjung,
                    ""
                )
            )
        }
        return characterList
    }
}