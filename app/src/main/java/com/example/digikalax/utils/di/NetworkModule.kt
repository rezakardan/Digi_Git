package com.example.digikalax.utils.di

import android.util.Log
import com.example.digikalax.data.datastore.SessionManager
import com.example.digikalax.data.models.mydigikala.BodyResponseLogin
import com.example.digikalax.data.networkk.ApiService
import com.example.digikalax.utils.constants.Constants
import com.example.digikalax.utils.constants.Constants.API_KEY
import com.example.digikalax.utils.constants.Constants.FA
import com.example.digikalax.utils.constants.Constants.LANG
import com.example.digikalax.utils.constants.Constants.X_API_KEY
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideBaseUrl() = Constants.BASE_URL

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    @Singleton
    fun provideInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun provideTimeOut() = Constants.TIMEOUT

    @Provides
    @Singleton
    @Named(Constants.NAMED_PING)
    fun providePingInterval() = Constants.PING_INTERVAL

    @Provides
    @Singleton
    fun provideOkHttpClient(
        time: Long,
        @Named(Constants.NAMED_PING) ping: Long,
        interceptor: HttpLoggingInterceptor,
        sessionManager: SessionManager,
        tokenManager: TokenManager // تزریق TokenManager از Hilt
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val token = runBlocking {
                    sessionManager.getUserToken.firstOrNull()?.toString().orEmpty()
                }

                val request = chain.request().newBuilder().also {
                    it.addHeader(X_API_KEY, API_KEY)
                    it.addHeader(LANG, FA)
                    it.addHeader("token", token)
                }.build()

                var response = chain.proceed(request)

                Log.d("Interceptor", "Response code: ${response.code}")
                if (response.code == 401) {
                    Log.d("InterceptorExpire", "Response code: ${response.code}")

                    val mobileNumber = runBlocking {
                        sessionManager.getUserPhone.firstOrNull()?.toString().orEmpty()
                    }
                    val password = runBlocking {
                        sessionManager.getUserPassword.firstOrNull()?.toString().orEmpty()
                    }

                    // اینجا از TokenManager تزریق شده استفاده می‌کنیم
                    val newToken = runBlocking {
                        tokenManager.getNewAccessTokenUsingLogin(mobileNumber, password)
                    }

                    val newRequest = request.newBuilder()
                        .header("token", newToken)
                        .build()

                    response.close()
                    response = chain.proceed(newRequest)

                    Log.d("Interceptor", "Token expired, requesting new token")
                }

                response
            }
            .also { client ->
                client.addInterceptor(interceptor)
            }
            .readTimeout(time, TimeUnit.SECONDS)
            .writeTimeout(time, TimeUnit.SECONDS)
            .connectTimeout(time, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .pingInterval(ping, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(client: OkHttpClient, gson: Gson, baseUrl: String): ApiService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
            .create(ApiService::class.java)
    }
}


