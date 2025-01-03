package com.example.digikalax.utils.di

import com.example.digikalax.data.datastore.SessionManager
import com.example.digikalax.data.models.mydigikala.BodyResponseLogin
import com.example.digikalax.data.networkk.ApiService
import javax.inject.Inject
import javax.inject.Provider

class TokenManager @Inject constructor(
    private val sessionManager: SessionManager,
    private val apiServiceProvider: Provider<ApiService> // استفاده از Provider برای دسترسی تاخیری به ApiService
) {

    suspend fun getNewAccessTokenUsingLogin(
        mobileNumber: String,
        password: String
    ): String {
        val body = BodyResponseLogin(password, mobileNumber)

        // دریافت ApiService در زمان نیاز
        val apiService = apiServiceProvider.get()

        val response = apiService.loginAndVerify(body)

        if (response.isSuccessful) {
            val newAccessToken = response.body()?.data?.token.orEmpty()
            sessionManager.saveToken(newAccessToken)
            return newAccessToken
        } else {
            throw Exception("Login failed: ${response.message()}")
        }
    }
}