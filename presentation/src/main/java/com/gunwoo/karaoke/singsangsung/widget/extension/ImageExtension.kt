package com.gunwoo.karaoke.singsangsung.widget.extension

import android.content.res.ColorStateList
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import com.bumptech.glide.Glide
import com.gunwoo.karaoke.singsangsung.R

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