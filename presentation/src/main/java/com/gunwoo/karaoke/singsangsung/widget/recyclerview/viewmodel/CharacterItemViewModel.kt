package com.gunwoo.karaoke.singsangsung.widget.recyclerview.viewmodel

import androidx.lifecycle.MutableLiveData
import com.gunwoo.karaoke.domain.model.Character
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.singsangsung.base.viewmodel.BaseItemViewModel
import com.gunwoo.karaoke.singsangsung.widget.recyclerview.navigator.CharacterNavigator
import com.gunwoo.karaoke.singsangsung.widget.recyclerview.navigator.MusicNavigator

class CharacterItemViewModel : BaseItemViewModel<CharacterNavigator>() {

    private lateinit var character: Character

    val profileImage = MutableLiveData<String>()
    val name = MutableLiveData<String>()

    fun bind(data: Character) {
        character = data

        name.value = character.name
        profileImage.value = character.profileImage
    }

    fun onClickItem() { getNavigator().onClickItem(character) }
}