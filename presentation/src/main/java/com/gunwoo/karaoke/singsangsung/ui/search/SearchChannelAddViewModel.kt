package com.gunwoo.karaoke.singsangsung.ui.search

import com.gunwoo.karaoke.domain.model.SearchSetting
import com.gunwoo.karaoke.domain.usecase.searchsetting.GetNotSelectedSearchSettingListUseCase
import com.gunwoo.karaoke.domain.usecase.searchsetting.InsertSearchSettingUseCase
import com.gunwoo.karaoke.singsangsung.base.viewmodel.BaseViewModel
import com.gunwoo.karaoke.singsangsung.widget.SingleLiveEvent
import com.gunwoo.karaoke.singsangsung.widget.recyclerview.channel.ChannelListAdapter
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableSingleObserver

class SearchChannelAddViewModel(
    private val getNotSelectedSearchSettingListUseCase: GetNotSelectedSearchSettingListUseCase,
    private val insertSearchSettingUseCase: InsertSearchSettingUseCase
) : BaseViewModel() {

    val searchSettingList = ArrayList<SearchSetting>()

    val channelListAdapter =
        ChannelListAdapter()

    val onSuccessInsertSearchSettingEvent = SingleLiveEvent<SearchSetting>()
    val onBackEvent = SingleLiveEvent<Unit>()

    init {
        channelListAdapter.setSearchSettingList(searchSettingList)
        channelListAdapter.setViewType(ChannelListAdapter.ViewType.NOT_SELECTED_CHANNEL)
    }

    fun setSearchSettingList() {
        addDisposable(getNotSelectedSearchSettingListUseCase.buildUseCaseObservable(),
            object : DisposableSingleObserver<List<SearchSetting>>() {
                override fun onSuccess(t: List<SearchSetting>) {
                    searchSettingList.clear()
                    searchSettingList.addAll(t)
                    channelListAdapter.notifyDataSetChanged()
                }

                override fun onError(e: Throwable) {
                    onErrorEvent.value = e
                }
            })
    }

    fun insertSearchSetting(searchSetting: SearchSetting) {
        addDisposable(insertSearchSettingUseCase.buildUseCaseObservable(InsertSearchSettingUseCase.Params(searchSetting)),
            object : DisposableCompletableObserver() {
                override fun onComplete() {
                    onSuccessInsertSearchSettingEvent.value = searchSetting
                }

                override fun onError(e: Throwable) {
                    onErrorEvent.value = e
                }
            })
    }

    fun onClickBack() = onBackEvent.call()
}