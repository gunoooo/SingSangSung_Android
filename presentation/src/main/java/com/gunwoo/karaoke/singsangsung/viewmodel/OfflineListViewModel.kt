package com.gunwoo.karaoke.singsangsung.viewmodel

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import com.gunwoo.karaoke.domain.model.Download
import com.gunwoo.karaoke.domain.usecase.download.DeleteDownloadUseCase
import com.gunwoo.karaoke.singsangsung.base.viewmodel.BaseViewModel
import com.gunwoo.karaoke.singsangsung.widget.SingleLiveEvent
import com.gunwoo.karaoke.singsangsung.widget.recyclerview.adapter.OfflineMusicListAdapter
import io.reactivex.observers.DisposableCompletableObserver


class OfflineListViewModel(
    private val deleteDownloadUseCase: DeleteDownloadUseCase
) : BaseViewModel() {

    val downloadList = ArrayList<Download>()

    val thumbnail = MutableLiveData<Uri>()
    val description = MutableLiveData<String>()

    val offlineMusicListAdapter = OfflineMusicListAdapter()

    val onSuccessDeleteDownloadEvent = SingleLiveEvent<Unit>()

    init { offlineMusicListAdapter.setDownloadList(downloadList) }

    fun setData(downloadList: List<Download>) {
        thumbnail.value = Uri.fromFile(downloadList[0].thumbnail)
        description.value = "총 ${downloadList.size}곡  |  보관함"
        this.downloadList.clear()
        this.downloadList.addAll(downloadList)
        offlineMusicListAdapter.notifyDataSetChanged()
    }

    fun deleteDownload(download: Download) {
        addDisposable(deleteDownloadUseCase.buildUseCaseObservable(DeleteDownloadUseCase.Params(download)),
            object : DisposableCompletableObserver() {
                override fun onComplete() {
                    val position = downloadList.indexOf(download)
                    downloadList.remove(download)
                    offlineMusicListAdapter.notifyItemRemoved(position)
                    onSuccessDeleteDownloadEvent.call()
                }

                override fun onError(e: Throwable) {
                    onErrorEvent.value = e
                }
            })
    }
}