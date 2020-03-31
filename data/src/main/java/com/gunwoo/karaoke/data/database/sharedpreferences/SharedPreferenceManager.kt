package com.gunwoo.karaoke.data.database.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import com.gunwoo.karaoke.data.util.dateFormat
import java.util.*


object SharedPreferenceManager {

    private const val PREF_UPDATE_DATE = "update"

    fun insertUpdateDate(context: Context, updateDate: String) {
        getDefaultSharedPreferences(context).edit().putString(PREF_UPDATE_DATE, updateDate).apply()
    }

    fun getUpdateDate(context: Context): String? {
        return getDefaultSharedPreferences(context).getString(PREF_UPDATE_DATE, Date().dateFormat())
    }

    private fun getDefaultSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(
            getDefaultSharedPreferencesName(context),
            getDefaultSharedPreferencesMode()
        )
    }

    private fun getDefaultSharedPreferencesName(context: Context): String {
        return context.packageName.toString() + "_preferences"
    }

    private fun getDefaultSharedPreferencesMode(): Int {
        return Context.MODE_PRIVATE
    }
}