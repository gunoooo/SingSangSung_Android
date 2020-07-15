package com.gunwoo.karaoke.singsangsung.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.gunwoo.karaoke.singsangsung.R

class SingSangSungLoadingView : FrameLayout {

    private var mContext: Context? = null
    private var attrs: AttributeSet? = null
    private var styleAttr: Int? = null
    private var view = View.inflate(context, R.layout.item_loading, null)

    constructor(context: Context) : super(context) {
        init(context, null, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs, null)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs, defStyleAttr)
    }

    private fun init(context: Context, attrs: AttributeSet?, defStyleAttr: Int?) {
        this.mContext = context
        this.attrs = attrs
        this.styleAttr = defStyleAttr

        addView(view)
    }
}