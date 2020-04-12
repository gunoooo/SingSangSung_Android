package com.gunwoo.karaoke.singsangsung.ui.favorites

import com.gunwoo.karaoke.domain.model.Favorites
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.domain.usecase.favorites.DeleteFavoritesItemUseCase
import com.gunwoo.karaoke.domain.usecase.favorites.GetFavoritesListUseCase
import com.gunwoo.karaoke.domain.usecase.favorites.InsertFavoritesItemUseCase
import com.gunwoo.karaoke.singsangsung.base.viewmodel.BaseViewModel
import com.gunwoo.karaoke.singsangsung.widget.SingleLiveEvent
import com.gunwoo.karaoke.singsangsung.widget.recyclerview.favorites.FavoritesCheckListAdapter
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableSingleObserver

class FavoritesBottomSheetViewModel(
    private val getFavoritesListUseCase: GetFavoritesListUseCase,
    private val insertFavoritesItemUseCase: InsertFavoritesItemUseCase,
    private val deleteFavoritesItemUseCase: DeleteFavoritesItemUseCase
) : BaseViewModel() {

    lateinit var youtubeData: YoutubeData

    val favoritesList = ArrayList<Favorites>()

    val favoritesCheckListAdapter = FavoritesCheckListAdapter(favoritesList)

    val onOpenFavoritesAddViewEvent = SingleLiveEvent<Unit>()
    val onCloseEvent = SingleLiveEvent<Unit>()

    fun setFavoritesList() {
        addDisposable(getFavoritesListUseCase.buildUseCaseObservable(GetFavoritesListUseCase.Params(youtubeData.videoId)),
            object : DisposableSingleObserver<List<Favorites>>() {
                override fun onSuccess(t: List<Favorites>) {
                    favoritesList.clear()
                    favoritesList.addAll(t)
                    favoritesCheckListAdapter.notifyDataSetChanged()
                }

                override fun onError(e: Throwable) {
                    onErrorEvent.value = e
                }
            })
    }

    fun insertFavoritesItem(favoritesId: Int) {
        addDisposable(insertFavoritesItemUseCase.buildUseCaseObservable(InsertFavoritesItemUseCase.Params(youtubeData, favoritesId)),
            object : DisposableCompletableObserver() {
                override fun onComplete() { }

                override fun onError(e: Throwable) {
                    onErrorEvent.value = e
                }
            })
    }

    fun deleteFavoritesItem(favoritesId: Int) {
        addDisposable(deleteFavoritesItemUseCase.buildUseCaseObservable(DeleteFavoritesItemUseCase.Params(youtubeData, favoritesId)),
            object : DisposableCompletableObserver() {
                override fun onComplete() { }

                override fun onError(e: Throwable) {
                    onErrorEvent.value = e
                }
            })
    }

    fun onClickClose() { onCloseEvent.call() }

    fun onClickAddFavorites() { onOpenFavoritesAddViewEvent.call() }
}