package dev.dicesystems.varsitutor.util

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInterceptor @Inject constructor(private val preferenceManager: PreferenceManager) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val accessToken = preferenceManager.getToken()

        val request = if (accessToken != null) {
            chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $accessToken")
                .build()
        } else {
            chain.request()
        }
        return chain.proceed(request)
    }
}