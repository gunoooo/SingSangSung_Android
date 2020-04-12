package com.gunwoo.karaoke.singsangsung.widget.recyclerview.favorites

import android.view.View
import android.widget.LinearLayout
import android.widget.PopupMenu
import androidx.lifecycle.MutableLiveData
import com.gunwoo.karaoke.domain.model.Favorites
import com.gunwoo.karaoke.singsangsung.R
import com.gunwoo.karaoke.singsangsung.base.viewmodel.BaseItemViewModel

class FavoritesItemViewModel : BaseItemViewModel<FavoritesNavigator>() {

    private lateinit var favorites: Favorites

    val thumbnail = MutableLiveData<String>()
    val title = MutableLiveData<String>()
    val createDate = MutableLiveData<String>()
    val videoCount = MutableLiveData<String>()

    fun bind(data: Favorites) {
        favorites = data

        thumbnail.value = if (favorites.favoritesItemList.isNullOrEmpty()) null else favorites.favoritesItemList!![0].thumbnailUrl
        title.value = favorites.title
        createDate.value = favorites.createDate
        videoCount.value = "동영상 ${favorites.favoritesItemList?.size ?: 0}개"
    }

    fun onClickItem() {
        getNavigator().onClickItem(favorites)
    }

    fun onClickMenu(view: View) {
        val menuButton = view as LinearLayout

        val popup = PopupMenu(view.context, menuButton)
        popup.inflate(R.menu.menu_favorites_item)

        popup.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.menu_play -> {
                    getNavigator().play(favorites)
                    true
                }
                R.id.menu_fix_title -> {
                    getNavigator().fixTitle(favorites)
                    true
                }
                R.id.menu_delete -> {
                    getNavigator().delete(favorites)
                    true
                }
                else -> false
            }
        }

        popup.show()
    }
}