package com.gunwoo.karaoke.singsangsung.viewmodel

import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.domain.usecase.DeleteFavoritesUseCase
import com.gunwoo.karaoke.domain.usecase.InsertDownloadUseCase
import com.gunwoo.karaoke.domain.usecase.InsertFavoritesUseCase
import com.gunwoo.karaoke.domain.usecase.InsertHidingUseCase
import com.gunwoo.karaoke.singsangsung.base.viewmodel.BasePlaylistViewModel

class PlayerPlaylistViewModel(
    insertDownloadUseCase: InsertDownloadUseCase,
    insertFavoritesUseCase: InsertFavoritesUseCase,
    insertHidingUseCase: InsertHidingUseCase,
    deleteFavoritesUseCase: DeleteFavoritesUseCase
) : BasePlaylistViewModel(insertDownloadUseCase, insertFavoritesUseCase, insertHidingUseCase, deleteFavoritesUseCase) {

    fun setYoutubeDataList(youtubeDataList: List<YoutubeData>) {
        this.youtubeDataList.clear()
        this.youtubeDataList.addAll(youtubeDataList)
        musicListAdapter.notifyDataSetChanged()
    }
}