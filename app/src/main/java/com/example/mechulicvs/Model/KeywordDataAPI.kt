package com.example.mechulicvs.Model

import com.example.mechulicvs.MainApplication
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object KeywordDataAPI {

    val keyword = MainApplication.prefs.getString("keyword", "")

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