package com.gunwoo.karaoke.singsangsung.viewmodel

import com.gunwoo.karaoke.domain.model.SearchSetting
import com.gunwoo.karaoke.domain.usecase.searchsetting.DeleteSearchSettingUseCase
import com.gunwoo.karaoke.domain.usecase.searchsetting.GetSearchSettingListUseCase
import com.gunwoo.karaoke.domain.usecase.searchsetting.UpdateSearchSettingListUseCase
import com.gunwoo.karaoke.domain.usecase.searchsetting.UpdateSearchSettingUseCase
import com.gunwoo.karaoke.singsangsung.base.viewmodel.BaseViewModel
import com.gunwoo.karaoke.singsangsung.widget.SingleLiveEvent
import com.gunwoo.karaoke.singsangsung.widget.recyclerview.adapter.ChannelListAdapter
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableSingleObserver
import java.util.*
import kotlin.collections.ArrayList

class SearchSettingViewModel(
    private val getSearchSettingListUseCase: GetSearchSettingListUseCase,
    private val deleteSearchSettingUseCase: DeleteSearchSettingUseCase,
    private val updateSearchSettingUseCase: UpdateSearchSettingUseCase,
    private val updateSearchSettingListUseCase: UpdateSearchSettingListUseCase
) : BaseViewModel() {

    val searchSettingList = ArrayList<SearchSetting>()

    val channelListAdapter = ChannelListAdapter()

    val onErrorListOverEvent = SingleLiveEvent<Unit>()
    val onOpenChannelAddViewEvent = SingleLiveEvent<Unit>()

    init {
        channelListAdapter.setSearchSettingList(searchSettingList)
        channelListAdapter.setViewType(ChannelListAdapter.ViewType.SELECTED_CHANNEL)
        setSearchSettingList()
    }

    private fun setSearchSettingList() {
        isLoading.value = true
        addDisposable(getSearchSettingListUseCase.buildUseCaseObservable(),
            object : DisposableSingleObserver<List<SearchSetting>>() {
                override fun onSuccess(t: List<SearchSetting>) {
                    searchSettingList.clear()
                    searchSettingList.addAll(t)
                    channelListAdapter.notifyDataSetChanged()
                    isLoading.value = false
                }

                override fun onError(e: Throwable) {
                    onErrorEvent.value = e
                    isLoading.value = false
                }
            })
    }

    fun insertSearchSetting(searchSetting: SearchSetting) {
        searchSettingList.add(searchSetting)
        channelListAdapter.notifyItemInserted(searchSettingList.size - 1)
    }

    fun updateSearchSetting(searchSetting: SearchSetting) {
        addDisposable(updateSearchSettingUseCase.buildUseCaseObservable(UpdateSearchSettingUseCase.Params(searchSetting)),
            object : DisposableCompletableObserver() {
                override fun onComplete() { }

                override fun onError(e: Throwable) {
                    onErrorEvent.value = e
                }
            })
    }

    fun deleteSearchSetting(searchSetting: SearchSetting) {
        addDisposable(deleteSearchSettingUseCase.buildUseCaseObservable(DeleteSearchSettingUseCase.Params(searchSetting)),
            object : DisposableCompletableObserver() {
                override fun onComplete() {
                    val position = searchSettingList.indexOf(searchSetting)
                    searchSettingList.remove(searchSetting)
                    channelListAdapter.notifyItemRemoved(position)
                }

                override fun onError(e: Throwable) {
                    onErrorEvent.value = e
                }
            })
    }

    private fun updateSearchSettingList() {
        addDisposable(updateSearchSettingListUseCase.buildUseCaseObservable(UpdateSearchSettingListUseCase.Params(searchSettingList)),
            object : DisposableCompletableObserver() {
                override fun onComplete() { }

                override fun onError(e: Throwable) {
                    onErrorEvent.value = e
                }
            })
    }

    fun onClickAddChannel() {
        if (searchSettingList.size > 4)
            onErrorListOverEvent.call()
        else
            onOpenChannelAddViewEvent.call()
    }

    fun onDragged(from: Int, to: Int): Boolean {
        Collections.swap(searchSettingList, from, to)
        channelListAdapter.notifyItemMoved(from, to)
        updateSearchSettingList()

        return true
    }
}