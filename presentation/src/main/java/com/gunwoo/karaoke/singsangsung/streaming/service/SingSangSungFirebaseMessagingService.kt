package com.gunwoo.karaoke.singsangsung.streaming.service

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.gunwoo.karaoke.data.database.sharedpreferences.SharedPreferenceManager
import com.gunwoo.karaoke.data.util.dateFormat
import com.gunwoo.karaoke.domain.usecase.playlist.DeleteAllPlaylistUseCase
import com.gunwoo.karaoke.domain.usecase.search.DeleteAllSearchUseCase
import com.gunwoo.karaoke.singsangsung.streaming.notification.SingSangSungNotification
import dagger.android.AndroidInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject


class SingSangSungFirebaseMessagingService : FirebaseMessagingService() {

    @Inject
    lateinit var deleteAllPlaylistUseCase: DeleteAllPlaylistUseCase

    @Inject
    lateinit var deleteAllSearchUseCase: DeleteAllSearchUseCase

    @Inject
    lateinit var disposable: CompositeDisposable

    override fun onNewToken(token: String) { }

    override fun onCreate() {
        AndroidInjection.inject(this)
        super.onCreate()
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        if (remoteMessage.notification == null) return

        SingSangSungNotification.showNotification(this, remoteMessage.notification!!.body, remoteMessage.notification!!.title!!)

        disposable.add(
            deleteAllPlaylistUseCase.buildUseCaseObservable()
                .andThen(deleteAllSearchUseCase.buildUseCaseObservable())
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe { SharedPreferenceManager.insertUpdateDate(this, Date().dateFormat()) }
        )
    }
}