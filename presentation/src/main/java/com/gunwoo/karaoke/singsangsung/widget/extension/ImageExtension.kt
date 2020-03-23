package com.gunwoo.karaoke.singsangsung.widget.extension

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.net.Uri
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import com.gunwoo.karaoke.data.util.Constants
import com.gunwoo.karaoke.singsangsung.R
import java.io.*
import java.nio.channels.FileChannel
import java.util.*

fun ImageView.putImage(url: String?) {
    Glide.with(this.context)
        .load(url)
        .error(R.color.colorLightGrey)
        .into(this)
}

fun ImageView.putImage(resource: Int) {
    Glide.with(this.context)
        .load(resource)
        .error(R.color.colorLightGrey)
        .into(this)
}

fun ImageView.setImageTint(resource: Int) {
    val tint = ContextCompat.getColor(context, resource)
    ImageViewCompat.setImageTintList(this, ColorStateList.valueOf(tint))
}