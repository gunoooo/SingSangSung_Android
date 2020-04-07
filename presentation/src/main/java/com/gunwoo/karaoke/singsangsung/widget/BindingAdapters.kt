package com.gunwoo.karaoke.singsangsung.widget

import android.annotation.SuppressLint
import android.net.Uri
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import com.gunwoo.karaoke.singsangsung.R
import com.gunwoo.karaoke.singsangsung.widget.extension.getParentActivity
import java.text.SimpleDateFormat
import java.util.*


@BindingAdapter("adapter")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

@BindingAdapter("cardViewChecked")
fun setCardViewChecked(view: MaterialCardView, check: MutableLiveData<Boolean>) {
    val parentActivity: AppCompatActivity = view.getParentActivity() ?: return

    check.observe(parentActivity, Observer { value -> view.isChecked = value?:false })
}

@BindingAdapter("checkBoxChecked")
fun setMutableChecked(view: CheckBox, check: MutableLiveData<Boolean>) {
    val parentActivity: AppCompatActivity = view.getParentActivity() ?: return

    check.observe(parentActivity, Observer { value -> view.isChecked = value?:false })
}

@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View,  visibility: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity = view.getParentActivity() ?: return

    visibility?.observe(parentActivity, Observer { value -> view.visibility = value?:View.VISIBLE})
}

@BindingAdapter("mutableText")
fun setMutableText(view: TextView,  text: MutableLiveData<String>?) {
    val parentActivity: AppCompatActivity = view.getParentActivity() ?: return

    text?.observe(parentActivity, Observer { value -> view.text = value?:""})
}

@BindingAdapter("mutableDateText")
fun setMutableDateText(view: TextView,  text: MutableLiveData<Date>?) {
    val parentActivity: AppCompatActivity = view.getParentActivity() ?: return

    @SuppressLint("SimpleDateFormat")
    val format = SimpleDateFormat("yyyy-MM-dd  E")

    text?.observe(parentActivity, Observer { value -> view.text = format.format(value)?:""})
}

@BindingAdapter("mutableImageDrawable")
fun setMutableImageDrawable(view: ImageView, resid: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity = view.getParentActivity() ?: return

    if(resid != null) {
        resid.observe(parentActivity, Observer { value -> view.setImageResource(value)})
    }
    else {
        Glide.with(view.context)
            .load(R.color.colorLightGrey)
            .into(view)
    }
}

@BindingAdapter("mutableImageUrl")
fun setMutableImageUrl(view: ImageView, url: MutableLiveData<String>?) {
    val parentActivity: AppCompatActivity = view.getParentActivity() ?: return

    if(url != null) {
        url.observe(parentActivity, Observer { value -> Glide.with(view.context)
            .load(value)
            .error(R.color.colorLightGrey)
            .into(view)})
    }
    else {
        Glide.with(view.context)
            .load(R.color.colorLightGrey)
            .into(view)
    }
}

@BindingAdapter("mutableImageUri")
fun setMutableImageUri(view: ImageView, uri: MutableLiveData<Uri>?) {
    val parentActivity: AppCompatActivity = view.getParentActivity() ?: return

    if(uri != null) {
        uri.observe(parentActivity, Observer { value -> Glide.with(view.context)
            .load(value)
            .error(R.color.colorLightGrey)
            .into(view)})
    }
    else {
        Glide.with(view.context)
            .load(R.color.colorLightGrey)
            .into(view)
    }
}

@BindingAdapter("mutableSpinnerPosition")
fun setMutableSpinnerPosition(view: Spinner, spinnerPosition: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity = view.getParentActivity() ?: return

    spinnerPosition?.observe(parentActivity, Observer { value ->
        view.setSelection(value, false)
    })
}

@BindingAdapter("dragListener")
fun setDragListener(recyclerView: RecyclerView, onItemDrag: DragItemTouchHelperCallback.OnItemCallbackListener) {
    val itemTouchHelper = ItemTouchHelper(DragItemTouchHelperCallback(onItemDrag))
    itemTouchHelper.attachToRecyclerView(recyclerView)
}