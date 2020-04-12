package com.gunwoo.karaoke.singsangsung.widget.recyclerview.character

import androidx.lifecycle.MutableLiveData
import com.gunwoo.karaoke.domain.model.Character
import com.gunwoo.karaoke.singsangsung.base.viewmodel.BaseItemViewModel
import com.gunwoo.karaoke.singsangsung.widget.recyclerview.character.CharacterNavigator

class CharacterItemViewModel : BaseItemViewModel<CharacterNavigator>() {

    private lateinit var character: Character

    val profileImage = MutableLiveData<Int>()
    val name = MutableLiveData<String>()

    fun bind(data: Character) {
        character = data

        name.value = character.name
        profileImage.value = character.profileImage
    }

    fun onClickItem() { getNavigator().onClickItem(character) }
}