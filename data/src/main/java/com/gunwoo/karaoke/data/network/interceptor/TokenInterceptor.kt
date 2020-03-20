package com.gunwoo.karaoke.data.network.interceptor

import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class TokenInterceptor @Inject constructor(
    private val credential: GoogleAccountCredential
) : Interceptor {

    private var token: String? = null

    override fun intercept(chain: Interceptor.Chain): Response {
        token = credential.token

        val request =
            if (token != null) chain.request().newBuilder().header("Authorization", "Bearer $token").build()
            else chain.request()

        return chain.proceed(request)
    }
}