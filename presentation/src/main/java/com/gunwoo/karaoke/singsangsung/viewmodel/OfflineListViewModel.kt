package com.gunwoo.karaoke.singsangsung.viewmodel

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import com.gunwoo.karaoke.domain.model.Download
import com.gunwoo.karaoke.singsangsung.base.viewmodel.BaseViewModel
import com.gunwoo.karaoke.singsangsung.widget.recyclerview.adapter.OfflineMusicListAdapter


class OfflineListViewModel : BaseViewModel() {

    val downloadList = ArrayList<Download>()

    val thumbnail = MutableLiveData<Uri>()
    val description = MutableLiveData<String>()

    val offlineMusicListAdapter = OfflineMusicListAdapter()

    init { offlineMusicListAdapter.setDownloadList(downloadList) }

    fun setData(downloadList: List<Download>) {
        thumbnail.value = if (downloadList[0].thumbnail != null) Uri.fromFile(downloadList[0].thumbnail) else null
        description.value = "총 ${downloadList.size}곡  |  다운로드"
        this.downloadList.clear()
        this.downloadList.addAll(downloadList)
        offlineMusicListAdapter.notifyDataSetChanged()
    }
}