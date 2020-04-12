package com.gunwoo.karaoke.singsangsung.widget.recyclerview.music

import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.widget.PopupMenu
import androidx.lifecycle.MutableLiveData
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.singsangsung.R
import com.gunwoo.karaoke.singsangsung.base.viewmodel.BaseItemViewModel
import com.gunwoo.karaoke.singsangsung.widget.recyclerview.music.MusicNavigator

class MusicItemViewModel : BaseItemViewModel<MusicNavigator>() {

    private lateinit var youtubeData: YoutubeData
    private lateinit var viewType: MusicListAdapter.ViewType

    val thumbnail = MutableLiveData<String>()
    val title = MutableLiveData<String>()

    fun bind(data: YoutubeData, viewType: MusicListAdapter.ViewType) {
        youtubeData = data
        this.viewType = viewType

        thumbnail.value = youtubeData.thumbnailUrl
        title.value = youtubeData.videoTitle
    }

    fun onClickItem() {
        if (!youtubeData.isHiding)
            getNavigator().onClickItem(youtubeData)
    }

    fun onClickMenu(view: View) {
        val menuButton = view as LinearLayout

        val popup = PopupMenu(view.context, menuButton)
        when {
            youtubeData.isHiding -> popup.inflate(R.menu.menu_hiding_music_item)
            viewType == MusicListAdapter.ViewType.FAVORITES -> popup.inflate(R.menu.menu_favorites_music_item)
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