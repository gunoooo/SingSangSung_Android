package com.gunwoo.karaoke.data.util

import android.os.Environment

object Constants {
    val DIRECTORY_DOWNLOADS = Environment.getExternalStorageDirectory().toString() + "/SingSangSung"
    const val DEFAULT_HOST = "https://www.googleapis.com/youtube/v3/"

    const val TIME_OUT_MESSAGE = "시간초과 다시 한번 시도해주세요"
    const val SERVER_ERROR_MESSAGE = "이용에 불편을 드려 죄송합니다\n추후에 다시 시도해주세요"

    const val PART_SNIPPET = "snippet"
    const val MAX_RESULT = 50
    const val TYPE = "video"

    const val TJ_CHANNEL_ID = "UCRJwJfdokINmcw-UsOojbMA"
    const val KY_CHANNEL_ID = "UCDqaUIUSJP5EVMEI178Zfag"
    const val CHILD_CHANNEL_ID = "UCFesyM0Lk__UhMNccHSJrlQ"
}