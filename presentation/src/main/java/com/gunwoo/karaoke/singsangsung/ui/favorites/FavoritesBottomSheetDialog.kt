package com.gunwoo.karaoke.singsangsung.ui.favorites

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.singsangsung.base.dialog.BaseBottomSheetDialog
import com.gunwoo.karaoke.singsangsung.databinding.DialogFavoritesBottomSheetBinding
import com.gunwoo.karaoke.singsangsung.widget.extension.getViewModel
import javax.inject.Inject

class FavoritesBottomSheetDialog(private val youtubeData: YoutubeData) : BaseBottomSheetDialog<DialogFavoritesBottomSheetBinding, FavoritesBottomSheetViewModel>() {

    @Inject
    lateinit var viewModelFactory: FavoritesBottomSheetViewModelFactory

    override val viewModel: FavoritesBottomSheetViewModel
        get() = getViewModel(viewModelFactory)

    override fun observerViewModel() {
        with(mViewModel) {
            with(favoritesCheckListAdapter) {
                onCheckFavoritesEvent.observe(this@FavoritesBottomSheetDialog, Observer {
                    insertFavoritesItem(it.id!!)
                })

                onUncheckFavoritesEvent.observe(this@FavoritesBottomSheetDialog, Observer {
                    deleteFavoritesItem(it.id!!)
                })
            }

            onOpenFavoritesAddViewEvent.observe(this@FavoritesBottomSheetDialog, Observer {
                val favoritesAddDialog = FavoritesAddDialog()
                favoritesAddDialog.show(fragmentManager!!)
                favoritesAddDialog.onSuccessInsertEvent.observe(this@FavoritesBottomSheetDialog, Observer {
                    setFavoritesList()
                })
            })

            onCloseEvent.observe(this@FavoritesBottomSheetDialog, Observer {
                dismiss()
            })
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.youtubeData = youtubeData
        mViewModel.setFavoritesList()
    }
}