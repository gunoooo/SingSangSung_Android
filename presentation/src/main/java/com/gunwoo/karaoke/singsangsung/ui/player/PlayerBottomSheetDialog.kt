package com.gunwoo.karaoke.singsangsung.ui.player

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.singsangsung.R
import com.gunwoo.karaoke.singsangsung.ui.favorites.FavoritesBottomSheetDialog
import com.gunwoo.karaoke.singsangsung.widget.SingleLiveEvent
import com.gunwoo.karaoke.singsangsung.widget.extension.putImage
import kotlinx.android.synthetic.main.dialog_player_bottom_sheet.*

class PlayerBottomSheetDialog(private val youtubeData: YoutubeData) : BottomSheetDialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_player_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        thumbnail.putImage(youtubeData.thumbnailUrl)
        title.text = youtubeData.videoTitle

        favorites_btn.setOnClickListener {
            FavoritesBottomSheetDialog(youtubeData).show(parentFragmentManager)
        }

        open_youtube_btn.setOnClickListener {
            openYoutube()
            dismiss()
        }

        share_btn.setOnClickListener {
            share()
            dismiss()
        }
    }

    private fun openYoutube() {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("http://youtube.com/watch?v=${youtubeData.videoId}")
            )
        )
    }

    private fun share() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, "http://youtube.com/watch?v=${youtubeData.videoId}")
        startActivity(Intent.createChooser(intent, "싱생송 - 무료 노래방"))
    }

    fun show(fragmentManager: FragmentManager?) {
        super.show(fragmentManager!!, tag)
    }
}