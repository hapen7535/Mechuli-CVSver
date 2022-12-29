package com.example.mechulicvs.model

import com.example.mechulicvs.model.Constants.Companion.BASE_URL
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object IdDataAPI {

    var userId : String = ""

    private val okHttpClient = OkHttpClient.Builder()
        .addNetworkInterceptor {
            val request = it.request()
                .newBuilder()
                .addHeader("userId", userId)
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

    val service : SendIdData by lazy {
        retrofit.create(SendIdData::class.java)
    }


}