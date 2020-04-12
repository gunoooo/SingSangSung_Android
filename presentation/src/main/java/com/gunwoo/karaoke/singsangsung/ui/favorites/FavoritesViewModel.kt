package com.gunwoo.karaoke.singsangsung.ui.favorites

import androidx.lifecycle.MutableLiveData
import com.gunwoo.karaoke.data.exception.ListEmptyException
import com.gunwoo.karaoke.domain.model.Favorites
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.domain.usecase.favorites.DeleteFavoritesUseCase
import com.gunwoo.karaoke.domain.usecase.favorites.GetFavoritesListUseCase
import com.gunwoo.karaoke.domain.usecase.hiding.GetHidingListUseCase
import com.gunwoo.karaoke.domain.usecase.recent.GetRecentListUseCase
import com.gunwoo.karaoke.singsangsung.base.viewmodel.BaseViewModel
import com.gunwoo.karaoke.singsangsung.widget.SingleLiveEvent
import com.gunwoo.karaoke.singsangsung.widget.recyclerview.favorites.FavoritesListAdapter
import com.gunwoo.karaoke.singsangsung.widget.recyclerview.music.MusicHorizontalListAdapter
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableSingleObserver

class FavoritesViewModel(
    private val getRecentListUseCase: GetRecentListUseCase,
    private val getFavoritesListUseCase: GetFavoritesListUseCase,
    private val getHidingListUseCase: GetHidingListUseCase,
    private val deleteFavoritesUseCase: DeleteFavoritesUseCase
) : BaseViewModel() {

    val recentList = ArrayList<YoutubeData>()
    val favoritesList = ArrayList<Favorites>()
    val hidingList = ArrayList<YoutubeData>()

    val recentListAdapter = MusicHorizontalListAdapter()
    val favoritesListAdapter = FavoritesListAdapter(favoritesList)

    val isEmptyRecentList = MutableLiveData<Boolean>()

    val onClickRecentListEvent = SingleLiveEvent<Unit>()
    val onOpenFavoritesAddViewEvent = SingleLiveEvent<Unit>()

    init { recentListAdapter.setYoutubeDataList(recentList) }

    fun setFavoritesList() {
        addDisposable(getFavoritesListUseCase.buildUseCaseObservable(GetFavoritesListUseCase.Params()),
            object : DisposableSingleObserver<List<Favorites>>() {
                override fun onSuccess(t: List<Favorites>) {
                    favoritesList.clear()
                    favoritesList.addAll(t)
                    favoritesListAdapter.notifyDataSetChanged()
                }

                override fun onError(e: Throwable) {
                    onErrorEvent.value = e
                }
            })
    }

    fun setRecentList() {
        addDisposable(getRecentListUseCase.buildUseCaseObservable(),
            object : DisposableSingleObserver<List<YoutubeData>>() {
                override fun onSuccess(t: List<YoutubeData>) {
                    isEmptyRecentList.value = false
                    recentList.clear()
                    recentList.addAll(t)
                    recentListAdapter.notifyDataSetChanged()
                }

                override fun onError(e: Throwable) {
                    if (e is ListEmptyException)
                        isEmptyRecentList.value = true
                    else
                        onErrorEvent.value = e
                }
            })
    }

    fun deleteFavorites(favorites: Favorites) {
        addDisposable(deleteFavoritesUseCase.buildUseCaseObservable(DeleteFavoritesUseCase.Params(favorites.id!!)),
            object : DisposableCompletableObserver() {
                override fun onComplete() {
                    val position = favoritesList.indexOf(favorites)
                    favoritesList.remove(favorites)
                    favoritesListAdapter.notifyItemRemoved(position)
                }

                override fun onError(e: Throwable) {
                    onErrorEvent.value = e
                }
            })
    }

    fun onClickRecentList() { onClickRecentListEvent.call() }

    fun onClickAddFavorites() { onOpenFavoritesAddViewEvent.call() }
}