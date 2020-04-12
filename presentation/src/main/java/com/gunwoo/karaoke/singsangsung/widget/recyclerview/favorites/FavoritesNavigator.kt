package com.gunwoo.karaoke.singsangsung.widget.recyclerview.favorites

import com.gunwoo.karaoke.domain.model.Favorites

interface FavoritesNavigator {
    fun onClickItem(favorites: Favorites)

    fun play(favorites: Favorites)

    fun fixTitle(favorites: Favorites)

    fun delete(favorites: Favorites)
}