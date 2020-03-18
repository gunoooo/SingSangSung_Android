package com.gunwoo.karaoke.data.base

import android.app.Application
import com.gunwoo.karaoke.data.database.RoomDatabase

open class BaseCache(application: Application) {
    protected val database = RoomDatabase.getInstance(application)!!
}