package com.gunwoo.karaoke.singsangsung.view.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.singsangsung.R
import com.gunwoo.karaoke.singsangsung.widget.SingleLiveEvent
import com.gunwoo.karaoke.singsangsung.widget.extension.putImage
import kotlinx.android.synthetic.main.dialog_player_bottom_sheet.*

class PlayerBottomSheetDialog(private val youtubeData: YoutubeData) : BottomSheetDialogFragment() {

    val onClickAddFavoritesEvent = SingleLiveEvent<Unit>()
    val onClickDownloadEvent = SingleLiveEvent<Unit>()
    val onClickDeleteFavoritesEvent = SingleLiveEvent<Unit>()
    val onClickDeleteDownloadEvent = SingleLiveEvent<Unit>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_player_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        thumbnail.putImage(youtubeData.thumbnailUrl)
        title.text = youtubeData.videoTitle

        when (youtubeData.state) {
            YoutubeData.State.NONE -> {
                download_text.text = getString(R.string.menu_download)
                favorites_text.text = getString(R.string.menu_add_favorites)
            }
            YoutubeData.State.DOWNLOAD -> {
                download_text.text = getString(R.string.menu_delete_download)
                favorites_text.text = getString(R.string.menu_add_favorites)
            }
            YoutubeData.State.FAVORITES -> {
                download_text.text = getString(R.string.menu_download)
                favorites_text.text = getString(R.string.menu_delete_favorites)
            }
            YoutubeData.State.FAVORITES_AND_DOWNLOAD -> {
                download_text.text = getString(R.string.menu_delete_download)
                favorites_text.text = getString(R.string.menu_delete_favorites)
            }
            else -> {
                download_text.text = getString(R.string.menu_download)
                favorites_text.text = getString(R.string.menu_add_favorites)
            }
        }

        download_btn.setOnClickListener {
            if (youtubeData.state == YoutubeData.State.DOWNLOAD || youtubeData.state == YoutubeData.State.FAVORITES_AND_DOWNLOAD)
                onClickDeleteDownloadEvent.call()
            else
                onClickDownloadEvent.call()

            dismiss()
        }

        favorites_btn.setOnClickListener {
            if (youtubeData.state == YoutubeData.State.FAVORITES || youtubeData.state == YoutubeData.State.FAVORITES_AND_DOWNLOAD)
                onClickDeleteFavoritesEvent.call()
            else
                onClickAddFavoritesEvent.call()

            dismiss()
        }
    }

    fun show(fragmentManager: FragmentManager?) {
        super.show(fragmentManager!!, tag)
    }
}