package com.gunwoo.karaoke.data.mapper

import com.gunwoo.karaoke.data.database.entity.FavoritesEntity
import com.gunwoo.karaoke.data.database.entity.FavoritesWithItemEntity
import com.gunwoo.karaoke.domain.model.Favorites

class FavoritesMapper {

    private val favoritesItemMapper = FavoritesItemMapper()

    fun mapToModel(entity: FavoritesWithItemEntity): Favorites {
        return Favorites(
            entity.favorites.id,
            entity.favorites.title,
            entity.favorites.createDate,
            entity.favoritesItemList.map { favoritesItemMapper.mapToModel(it) }
        )
    }

    fun mapToEntity(model: Favorites): FavoritesEntity {
        return FavoritesEntity(
            model.title,
            model.createDate
        )
    }
}