package com.gunwoo.karaoke.singsangsung.widget.recyclerview.viewmodel

import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.widget.PopupMenu
import androidx.lifecycle.MutableLiveData
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.singsangsung.R
import com.gunwoo.karaoke.singsangsung.base.viewmodel.BaseItemViewModel
import com.gunwoo.karaoke.singsangsung.widget.recyclerview.navigator.MusicNavigator

class MusicItemViewModel : BaseItemViewModel<MusicNavigator>() {

    private lateinit var youtubeData: YoutubeData

    val thumbnail = MutableLiveData<String>()
    val title = MutableLiveData<String>()

    fun bind(data: YoutubeData) {
        youtubeData = data

        thumbnail.value = youtubeData.thumbnailUrl
        title.value = youtubeData.videoTitle
    }

    fun onClickItem() {
        if (youtubeData.state != YoutubeData.State.HIDING)
            getNavigator().onClickItem(youtubeData)
    }

    fun onClickMenu(view: View) {
        val menuButton = view as LinearLayout

        val popup = PopupMenu(view.context, menuButton)
        when (youtubeData.state) {
            YoutubeData.State.NONE -> popup.inflate(R.menu.menu_music_item)
            YoutubeData.State.FAVORITES -> popup.inflate(R.menu.menu_favorites_music_item)
            YoutubeData.State.HIDING -> popup.inflate(R.menu.menu_hiding_music_item)
            else -> popup.inflate(R.menu.menu_music_item)
        }

        popup.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.menu_add_favorites -> {
                    getNavigator().addFavorites(youtubeData)
                    true
                }
                R.id.menu_delete_favorites -> {
                    getNavigator().deleteFavorites(youtubeData)
                    true
                }
                R.id.menu_hide -> {
                    getNavigator().hide(youtubeData)
                    true
                }
                R.id.menu_delete_hiding -> {
                    getNavigator().deleteHiding(youtubeData)
                    true
                }
                R.id.menu_open_youtube -> {
                    getNavigator().openYoutube(youtubeData)
                    true
                }
                R.id.menu_share -> {
                    getNavigator().share(youtubeData)
                    true
                }
                else -> false
            }
        }

        popup.show()
    }
}