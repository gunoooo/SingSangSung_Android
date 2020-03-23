package com.gunwoo.karaoke.singsangsung.widget.recyclerview.viewmodel

import android.net.Uri
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.widget.PopupMenu
import androidx.core.net.toUri
import androidx.lifecycle.MutableLiveData
import com.gunwoo.karaoke.domain.model.Download
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.singsangsung.R
import com.gunwoo.karaoke.singsangsung.base.viewmodel.BaseItemViewModel
import com.gunwoo.karaoke.singsangsung.widget.recyclerview.navigator.MusicNavigator
import com.gunwoo.karaoke.singsangsung.widget.recyclerview.navigator.OfflineMusicNavigator

class OfflineMusicItemViewModel : BaseItemViewModel<OfflineMusicNavigator>() {

    private lateinit var download: Download

    val thumbnail = MutableLiveData<Uri>()
    val title = MutableLiveData<String>()

    fun bind(data: Download) {
        download = data

        thumbnail.value = if (download.thumbnail != null) Uri.fromFile(download.thumbnail) else null
        title.value = download.title
    }

    fun onClickItem() { getNavigator().onClickItem(download) }
}