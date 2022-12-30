package com.example.mechulicvs.data.remote.api

import com.example.mechulicvs.data.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object KeywordDataAPI {

    var keyword : String = ""

    private val okHttpClient = OkHttpClient.Builder()
        .addNetworkInterceptor {
            val request = it.request()
                .newBuilder()
                .addHeader("keyword", keyword)
                .build()
            it.proceed(request)
        }
        .retryOnConnectionFailure(true)
        .build()

    private val retrofit by lazy {
        Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()
    }

    val getRatingDataService : GetRatingList by lazy {
        retrofit.create(GetRatingList::class.java)
    }

}