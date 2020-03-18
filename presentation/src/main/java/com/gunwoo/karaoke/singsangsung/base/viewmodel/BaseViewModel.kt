package com.gunwoo.karaoke.singsangsung.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gunwoo.karaoke.singsangsung.widget.SingleLiveEvent
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

/**
 * @author gunwoo
 *
 * BaseViewModel
 */
open class BaseViewModel : ViewModel() {
    private val disposable: CompositeDisposable = CompositeDisposable()

    protected val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    fun getIsLoading(): LiveData<Boolean> {
        return isLoading
    }

    val onErrorEvent = SingleLiveEvent<Throwable>()

    fun addDisposable(single: Single<*>, observer: DisposableSingleObserver<*>) {
        disposable.add(single.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(observer as SingleObserver<Any>) as Disposable)
    }
    fun addDisposable(completable: Completable, observer: DisposableCompletableObserver) {
        disposable.add(completable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribeWith(observer))
    }
    fun addDisposableSync(single: Single<*>, observer: DisposableSingleObserver<*>) {
        disposable.add(single.subscribeWith(observer as SingleObserver<Any>) as Disposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}