package com.gunwoo.karaoke.singsangsung.ui.favorites

import androidx.lifecycle.Observer
import com.gunwoo.karaoke.singsangsung.R
import com.gunwoo.karaoke.singsangsung.base.dialog.BaseDialog
import com.gunwoo.karaoke.singsangsung.databinding.DialogFavoritesAddBinding
import com.gunwoo.karaoke.singsangsung.widget.SingleLiveEvent
import com.gunwoo.karaoke.singsangsung.widget.extension.getViewModel
import com.gunwoo.karaoke.singsangsung.widget.extension.shortToast
import javax.inject.Inject

class FavoritesAddDialog : BaseDialog<DialogFavoritesAddBinding, FavoritesAddViewModel>() {

    @Inject
    lateinit var viewModelFactory: FavoritesAddViewModelFactory

    override val viewModel: FavoritesAddViewModel
        get() = getViewModel(viewModelFactory)

    val onSuccessInsertEvent = SingleLiveEvent<Unit>()

    override fun observerViewModel() {
        with(mViewModel) {
            onSuccessInsertEvent.observe(this@FavoritesAddDialog, Observer {
                this@FavoritesAddDialog.onSuccessInsertEvent.call()
                dismiss()
            })

            onEmptyEvent.observe(this@FavoritesAddDialog, Observer {
                shortToast(R.string.error_empty)
            })

            onBackEvent.observe(this@FavoritesAddDialog, Observer {
                dismiss()
            })
        }
    }
}