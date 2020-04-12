package com.gunwoo.karaoke.singsangsung.widget.recyclerview.favorites

import com.gunwoo.karaoke.domain.model.Favorites

interface FavoritesCheckNavigator {
    fun onCheckItem(favorites: Favorites, isChecked: Boolean)
}