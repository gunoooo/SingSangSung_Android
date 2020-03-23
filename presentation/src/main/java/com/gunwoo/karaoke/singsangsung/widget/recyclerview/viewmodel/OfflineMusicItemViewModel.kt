package com.gunwoo.karaoke.singsangsung.widget.recyclerview.viewmodel

import android.net.Uri
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.widget.PopupMenu
import androidx.lifecycle.MutableLiveData
import com.gunwoo.karaoke.domain.model.Download
import com.gunwoo.karaoke.singsangsung.R
import com.gunwoo.karaoke.singsangsung.base.viewmodel.BaseItemViewModel
import com.gunwoo.karaoke.singsangsung.widget.recyclerview.navigator.OfflineMusicNavigator

class OfflineMusicItemViewModel : BaseItemViewModel<OfflineMusicNavigator>() {

    private lateinit var download: Download

    val thumbnail = MutableLiveData<Uri>()
    val title = MutableLiveData<String>()

    fun bind(data: Download) {
        download = data

        thumbnail.value = Uri.fromFile(download.thumbnail)
        title.value = download.title
    }

    fun onClickItem() { getNavigator().onClickItem(download) }

    fun onClickMenu(view: View) {
        val menuButton = view as LinearLayout

        val popup = PopupMenu(view.context, menuButton)
        popup.inflate(R.menu.menu_download_music_item)

        popup.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.menu_delete_download -> {
                    getNavigator().deleteDownload(download)
                    true
                }
                else -> false
            }
        }

        popup.show()
    }
}