package com.gunwoo.karaoke.singsangsung.widget

import android.app.Activity
import android.app.Service
import com.gunwoo.karaoke.singsangsung.di.component.DaggerAppComponent
import dagger.android.*
import javax.inject.Inject


/**
 * @author gunwoo
 * <p>
 * 업데이트 시마다 업데이트 일자와 간략한 내용을 밑에 적어주세요
 * 스토어에 올라갈 버전 이름과 buil.gradle의 versionName은 아래와 같이 작성합니다.
 * </p>
 * <p>
 * <major>.<minor>.<point>  ex) 1.3.1
 * 이전 버전과 비교하여 UI나 기능상 큰 변경이 일어났을때 <major>를 1 증가시킵니다.
 * 하나의 새로운기능이나 앱 내의 중요 컨텐츠가 몇가지 변경되었을 경우 <minor>를 1 증가시킵니다.
 * 버그 수정등의 간단한 업데이트의 경우 맨 뒤 <point>를 1 증가시킵니다.
 * </p>
 * <p>
 * </p>
 */

class SingSangSungApplication : DaggerApplication(), HasActivityInjector, HasServiceInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var serviceInjector: DispatchingAndroidInjector<Service>

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder().application(this).build()

    override fun activityInjector(): DispatchingAndroidInjector<Activity> = activityInjector

    override fun serviceInjector(): DispatchingAndroidInjector<Service> = serviceInjector
}