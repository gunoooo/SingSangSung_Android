package com.gunwoo.karaoke.singsangsung.viewmodel

import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.singsangsung.base.viewmodel.BaseViewModel
import com.gunwoo.karaoke.singsangsung.widget.SingleLiveEvent
import com.gunwoo.karaoke.singsangsung.widget.recyclerview.adapter.MusicListAdapter
import java.util.*

class PlayerPlaylistViewModel : BaseViewModel() {

    val youtubeDataList = ArrayList<YoutubeData>()

    val musicListAdapter = MusicListAdapter()

    val onAddFavoritesEvent = SingleLiveEvent<YoutubeData>()
    val onDeleteFavoritesEvent = SingleLiveEvent<YoutubeData>()
    val onHideEvent = SingleLiveEvent<YoutubeData>()

    init { musicListAdapter.setYoutubeDataList(youtubeDataList) }

    fun setYoutubeDataList(youtubeDataList: List<YoutubeData>) {
        this.youtubeDataList.clear()
        this.youtubeDataList.addAll(youtubeDataList)
        musicListAdapter.notifyDataSetChanged()
    }

    fun addFavorites(youtubeData: YoutubeData) {
        youtubeDataList
            .forEachIndexed { index: Int, data: YoutubeData ->
                if (data.videoId == youtubeData.videoId) {
                    youtubeDataList[index].state = YoutubeData.State.FAVORITES
                    musicListAdapter.notifyItemChanged(index)
                }
            }

        onAddFavoritesEvent.value = youtubeData
    }

    fun deleteFavorites(youtubeData: YoutubeData) {
        youtubeDataList
            .forEachIndexed { index: Int, data: YoutubeData ->
                if (data.videoId == youtubeData.videoId) {
                    youtubeDataList[index].state = YoutubeData.State.NONE
                    musicListAdapter.notifyItemChanged(index)
                }
            }

        onDeleteFavoritesEvent.value = youtubeData
    }

    fun hide(youtubeData: YoutubeData) {
        val position = youtubeDataList.indexOf(youtubeData)
        youtubeDataList.remove(youtubeData)
        musicListAdapter.notifyItemRemoved(position)

        onHideEvent.value = youtubeData
    }
}