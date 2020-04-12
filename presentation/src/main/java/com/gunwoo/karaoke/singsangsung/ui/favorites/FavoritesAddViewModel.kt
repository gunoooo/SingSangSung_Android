package com.gunwoo.karaoke.singsangsung.ui.favorites

import androidx.lifecycle.MutableLiveData
import com.gunwoo.karaoke.domain.usecase.favorites.InsertFavoritesUseCase
import com.gunwoo.karaoke.singsangsung.base.viewmodel.BaseViewModel
import com.gunwoo.karaoke.singsangsung.widget.SingleLiveEvent
import io.reactivex.observers.DisposableCompletableObserver

class FavoritesAddViewModel(
    private val insertFavoritesUseCase: InsertFavoritesUseCase
) : BaseViewModel() {

    val title = MutableLiveData<String>()

    val onSuccessInsertEvent = SingleLiveEvent<Unit>()
    val onEmptyEvent = SingleLiveEvent<Unit>()
    val onBackEvent = SingleLiveEvent<Unit>()

    private fun insertFavorites() {
        addDisposable(insertFavoritesUseCase.buildUseCaseObservable(InsertFavoritesUseCase.Params(title.value!!)),
            object : DisposableCompletableObserver() {
                override fun onComplete() {
                    onSuccessInsertEvent.call()
                }

                override fun onError(e: Throwable) {
                    onErrorEvent.value = e
                }
            })
    }

    fun onClickBack() = onBackEvent.call()

    fun onClickApply() {
        if (title.value.isNullOrBlank())
            onEmptyEvent.call()
        else
            insertFavorites()
    }
}