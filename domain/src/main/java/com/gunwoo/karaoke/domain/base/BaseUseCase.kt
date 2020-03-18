package com.gunwoo.karaoke.domain.base

abstract class BaseUseCase<out T> {

    abstract fun buildUseCaseObservable(): T
}