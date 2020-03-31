package com.gunwoo.karaoke.singsangsung.widget.recyclerview.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gunwoo.karaoke.singsangsung.R
import com.gunwoo.karaoke.singsangsung.widget.extension.putImage
import com.smarteist.autoimageslider.SliderViewAdapter
import kotlinx.android.synthetic.main.item_image_slider.view.*


class ImageSliderAdapter : SliderViewAdapter<ImageSliderAdapter.ImageSliderItemViewHolder>() {

    private val imageList =
        listOf(
            R.drawable.img_banner_1,
            R.drawable.img_banner_2,
            R.drawable.img_banner_3
        )

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup): ImageSliderItemViewHolder {
        val inflate: View = LayoutInflater.from(parent.context).inflate(R.layout.item_image_slider, null)
        return ImageSliderItemViewHolder(inflate)
    }

    override fun onBindViewHolder(viewHolder: ImageSliderItemViewHolder, position: Int) {
        val image: Int = imageList[position]
        viewHolder.itemView.image.putImage(image)
    }

    override fun getCount(): Int {
        return imageList.size
    }

    inner class ImageSliderItemViewHolder(itemView: View) : ViewHolder(itemView)
}