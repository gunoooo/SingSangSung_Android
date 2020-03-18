package com.gunwoo.karaoke.singsangsung.viewmodelfactory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import com.gunwoo.karaoke.domain.usecase.GetSearchListUseCase
import javax.inject.Inject

open class SplashViewModelFactory @Inject constructor(
    private val context: Context,
    private val credential: GoogleAccountCredential
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        modelClass.getConstructor(
            Context::class.java,
            GoogleAccountCredential::class.java
        ).newInstance(context, credential)
}