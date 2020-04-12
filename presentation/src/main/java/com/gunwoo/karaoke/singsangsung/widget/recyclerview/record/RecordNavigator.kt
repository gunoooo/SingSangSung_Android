package com.gunwoo.karaoke.singsangsung.widget.recyclerview.record

import com.gunwoo.karaoke.domain.model.Record

interface RecordNavigator {
    fun onClickItem(record: Record)
}