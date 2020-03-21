package com.gunwoo.karaoke.singsangsung.widget.recyclerview.navigator

import com.gunwoo.karaoke.domain.model.Record

interface RecordNavigator {
    fun onClickItem(record: Record)
}