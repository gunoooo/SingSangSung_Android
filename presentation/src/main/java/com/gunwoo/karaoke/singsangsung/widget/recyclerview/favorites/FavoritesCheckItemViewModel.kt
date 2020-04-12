package com.gunwoo.karaoke.singsangsung.widget.recyclerview.favorites

import androidx.lifecycle.MutableLiveData
import com.gunwoo.karaoke.domain.model.Favorites
import com.gunwoo.karaoke.singsangsung.base.viewmodel.BaseItemViewModel
import com.gunwoo.karaoke.singsangsung.widget.recyclerview.favorites.FavoritesCheckNavigator

class FavoritesCheckItemViewModel : BaseItemViewModel<FavoritesCheckNavigator>() {

    private lateinit var favorites: Favorites

    val thumbnail = MutableLiveData<String>()
    val title = MutableLiveData<String>()
    val isChecked = MutableLiveData<Boolean>()

    fun bind(data: Favorites) {
        favorites = data

        thumbnail.value = if (favorites.favoritesItemList.isNullOrEmpty()) null else favorites.favoritesItemList!![0].thumbnailUrl
        title.value = favorites.title
        isChecked.value = favorites.isContained
    }

    fun onClickItem() {
        isChecked.value = !isChecked.value!!
        getNavigator().onCheckItem(favorites, isChecked.value!!)
    }
}