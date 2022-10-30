package com.example.mechulicvs.Model

import com.example.mechulicvs.MainApplication
import com.example.mechulicvs.Model.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object getRecomAPI {

    val userid = MainApplication.prefs.getString("userId", "")

    private val okHttpClient = OkHttpClient.Builder()
        .addNetworkInterceptor {
            val request = it.request()
                .newBuilder()
                .addHeader("userId", userid)
                .build()
            it.proceed(request)
        }
        .build()

    private val retrofit by lazy {
        Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    val recomservice : GetRecomData by lazy {
        retrofit.create(GetRecomData::class.java)
    }

}