package com.gunwoo.karaoke.singsangsung.widget.recyclerview.character

import com.gunwoo.karaoke.domain.model.Character

interface CharacterNavigator {
    fun onClickItem(character: Character)
}