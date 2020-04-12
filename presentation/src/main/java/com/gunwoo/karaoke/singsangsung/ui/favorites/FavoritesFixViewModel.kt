package com.gunwoo.karaoke.singsangsung.ui.favorites

import androidx.lifecycle.MutableLiveData
import com.gunwoo.karaoke.domain.model.Favorites
import com.gunwoo.karaoke.domain.usecase.favorites.InsertFavoritesUseCase
import com.gunwoo.karaoke.domain.usecase.favorites.UpdateFavoritesUseCase
import com.gunwoo.karaoke.singsangsung.base.viewmodel.BaseViewModel
import com.gunwoo.karaoke.singsangsung.widget.SingleLiveEvent
import io.reactivex.observers.DisposableCompletableObserver

class FavoritesFixViewModel(
    private val updateFavoritesUseCase: UpdateFavoritesUseCase
) : BaseViewModel() {

    lateinit var favorites: Favorites

    val title = MutableLiveData<String>()

    val onSuccessUpdateEvent = SingleLiveEvent<Unit>()
    val onEmptyEvent = SingleLiveEvent<Unit>()
    val onBackEvent = SingleLiveEvent<Unit>()

    private fun updateFavorites() {
        addDisposable(updateFavoritesUseCase.buildUseCaseObservable(UpdateFavoritesUseCase.Params(favorites.id!!, title.value!!)),
            object : DisposableCompletableObserver() {
                override fun onComplete() {
                    onSuccessUpdateEvent.call()
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
            updateFavorites()
    }
}