package com.gunwoo.karaoke.singsangsung.viewmodel

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import com.gunwoo.karaoke.data.database.sharedpreferences.SharedPreferenceManager
import com.gunwoo.karaoke.singsangsung.base.viewmodel.BaseViewModel
import com.gunwoo.karaoke.singsangsung.widget.SingleLiveEvent

class SplashViewModel(
    private val context: Context,
    val credential: GoogleAccountCredential
) : BaseViewModel() {

    private val isAccountEmpty
        get() = credential.selectedAccountName.isNullOrBlank()

    val onSuccessEvent = SingleLiveEvent<Unit>()
    val onOpenAccountPickerEvent = SingleLiveEvent<Unit>()
    val onOpenPermissionEvent = SingleLiveEvent<Unit>()

    init { checkAccount() }

    fun checkAccount() {
        if (isAccountEmpty)
            chooseAccount()
        else
            onSuccessEvent.call()
    }

    private fun chooseAccount() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.GET_ACCOUNTS) == PackageManager.PERMISSION_GRANTED) {
            val accountName: String? = SharedPreferenceManager.getAccountName(context)

            if (!accountName.isNullOrBlank()) {
                credential.selectedAccountName = accountName

                if (isAccountEmpty) {
                    SharedPreferenceManager.deleteAccountName(context)
                    chooseAccount()
                }

                onSuccessEvent.call()
            }
            else {
                onOpenAccountPickerEvent.call()
            }
        } else {
            onOpenPermissionEvent.call()
        }
    }
}