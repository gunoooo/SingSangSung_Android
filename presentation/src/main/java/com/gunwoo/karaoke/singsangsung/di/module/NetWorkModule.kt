package com.gunwoo.karaoke.singsangsung.di.module

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.gunwoo.karaoke.data.network.interceptor.ErrorResponseInterceptor
import com.gunwoo.karaoke.data.network.interceptor.TokenInterceptor
import com.gunwoo.karaoke.data.util.Constants
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
class NetWorkModule {

    @Provides
    @Singleton
    fun provideHttpClient(tokenInterceptor: TokenInterceptor): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val errorResponseInterceptor = ErrorResponseInterceptor()
        val okhttpBuilder = OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
            .addInterceptor(tokenInterceptor)
            .addInterceptor(errorResponseInterceptor)
        return okhttpBuilder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(Constants.DEFAULT_HOST)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .callbackExecutor(Executors.newSingleThreadExecutor())
            .build()
    }
}