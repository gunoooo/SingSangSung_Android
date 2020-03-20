package com.gunwoo.karaoke.singsangsung.widget.recyclerview.navigator

import com.gunwoo.karaoke.domain.model.Character

interface CharacterNavigator {
    fun onClickItem(character: Character)
}