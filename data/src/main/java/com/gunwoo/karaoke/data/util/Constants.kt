package com.gunwoo.karaoke.data.util

import android.os.Environment

object Constants {
    val DIRECTORY_RECORD = Environment.getExternalStorageDirectory().toString() + "/SingSangSung/record"
    val DIRECTORY_DOWNLOAD = Environment.getExternalStorageDirectory().toString() + "/SingSangSung/download"
    const val DEFAULT_HOST = "https://www.googleapis.com/youtube/v3/"

    const val TIME_OUT_MESSAGE = "시간초과 다시 한번 시도해주세요"
    const val SERVER_ERROR_MESSAGE = "이용에 불편을 드려 대단히 죄송합니다\n추후에 다시 시도해주세요"

    const val KY_CHANNEL_ID = "UCDqaUIUSJP5EVMEI178Zfag"
    const val MAGIC_GHANNEL_ID = "UCFumn6op29lOyzUU85bpQ2Q"
    const val MO_CHANNEL_ID = "UCu6lq1dzsQNoJ4M5ZY8Ozkw"
    const val ZZANG_CHANNEL_ID = "UCzv4mCu3YS_9WjAWSt9Xg9Q"
    const val LALA_CHANNEL_ID = "UCz9GBSHaCF4omH_7LdP5_CA"
    const val SSONG_CHANNEL_ID = "UCPyPwf5EH5RJr1plH97Dn4A"
    const val SINGIT_CHANNEL_ID = "UCwT5Ij1Dd-SBtudlduUlMKQ"
    const val FORU_CHANNEL_ID = "UCyYqXmPUO4InZT-E62F7hVg"
    const val CHILD_CHANNEL_ID = "UCFesyM0Lk__UhMNccHSJrlQ"

    const val POPULARITY_PLAYLIST_ID = "PLjGGC556n3Rg_bpB6ImldUWtPT1hm_Z2h"
    const val RECENTLY_PLAYLIST_ID = "PLjGGC556n3RiAeO0WuY9x1y6k3asGwALh"
    const val RECOMMEND_PLAYLIST_ID = "PLjGGC556n3RiEFdybkO8vGMtMkGcn1C_p"
}