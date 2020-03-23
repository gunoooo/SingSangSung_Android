package com.gunwoo.karaoke.singsangsung.widget.recyclerview.viewmodel

import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
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

    fun onClickItem() { getNavigator().onClickItem(youtubeData) }

    fun onClickMenu(view: View) {
        val menuButton = view as ImageView

        val popup = PopupMenu(view.context, menuButton)
        popup.inflate(R.menu.menu_music_item)

        popup.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.menu_download -> {
                    getNavigator().download(youtubeData)
                    true
                }
                R.id.menu_favorites -> {
                    getNavigator().addFavorites(youtubeData)
                    true
                }
                R.id.menu_hide -> {
                    getNavigator().hide(youtubeData)
                    true
                }
                else -> false
            }
        }

        popup.show()
    }
}