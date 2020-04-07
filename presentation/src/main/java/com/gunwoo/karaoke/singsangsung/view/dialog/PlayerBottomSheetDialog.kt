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
    val onClickDeleteFavoritesEvent = SingleLiveEvent<Unit>()
    val onClickOpenYoutubeEvent = SingleLiveEvent<Unit>()
    val onClickShareEvent = SingleLiveEvent<Unit>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_player_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        thumbnail.putImage(youtubeData.thumbnailUrl)
        title.text = youtubeData.videoTitle

        when (youtubeData.state) {
            YoutubeData.State.NONE -> {
                favorites_text.text = getString(R.string.menu_add_favorites)
                open_youtube_text.text = getString(R.string.menu_open_youtube)
                share_text.text = getString(R.string.menu_share)
            }
            YoutubeData.State.FAVORITES -> {
                favorites_text.text = getString(R.string.menu_delete_favorites)
                open_youtube_text.text = getString(R.string.menu_open_youtube)
                share_text.text = getString(R.string.menu_share)
            }
            else -> {
                favorites_text.text = getString(R.string.menu_add_favorites)
                open_youtube_text.text = getString(R.string.menu_open_youtube)
                share_text.text = getString(R.string.menu_share)
            }
        }

        favorites_btn.setOnClickListener {
            if (youtubeData.state == YoutubeData.State.FAVORITES)
                onClickDeleteFavoritesEvent.call()
            else
                onClickAddFavoritesEvent.call()

            dismiss()
        }

        open_youtube_btn.setOnClickListener {
            onClickOpenYoutubeEvent.call()
            dismiss()
        }

        share_btn.setOnClickListener {
            onClickShareEvent.call()
            dismiss()
        }
    }

    fun show(fragmentManager: FragmentManager?) {
        super.show(fragmentManager!!, tag)
    }
}