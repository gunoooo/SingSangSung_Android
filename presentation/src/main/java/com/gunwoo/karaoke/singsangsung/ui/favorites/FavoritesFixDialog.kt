package com.gunwoo.karaoke.singsangsung.ui.favorites

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.gunwoo.karaoke.domain.model.Favorites
import com.gunwoo.karaoke.singsangsung.R
import com.gunwoo.karaoke.singsangsung.base.dialog.BaseDialog
import com.gunwoo.karaoke.singsangsung.databinding.DialogFavoritesAddBinding
import com.gunwoo.karaoke.singsangsung.databinding.DialogFavoritesFixBinding
import com.gunwoo.karaoke.singsangsung.widget.SingleLiveEvent
import com.gunwoo.karaoke.singsangsung.widget.extension.getViewModel
import com.gunwoo.karaoke.singsangsung.widget.extension.shortToast
import kotlinx.android.synthetic.main.dialog_favorites_fix.*
import javax.inject.Inject

class FavoritesFixDialog(private val favorites: Favorites) : BaseDialog<DialogFavoritesFixBinding, FavoritesFixViewModel>() {

    @Inject
    lateinit var viewModelFactory: FavoritesFixViewModelFactory

    override val viewModel: FavoritesFixViewModel
        get() = getViewModel(viewModelFactory)

    val onSuccessUpdateEvent = SingleLiveEvent<Unit>()

    override fun observerViewModel() {
        with(mViewModel) {
            onSuccessUpdateEvent.observe(this@FavoritesFixDialog, Observer {
                this@FavoritesFixDialog.onSuccessUpdateEvent.call()
                dismiss()
            })

            onEmptyEvent.observe(this@FavoritesFixDialog, Observer {
                shortToast(R.string.error_empty)
            })

            onBackEvent.observe(this@FavoritesFixDialog, Observer {
                dismiss()
            })
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.favorites = favorites
        input_text.setText(favorites.title)
    }
}