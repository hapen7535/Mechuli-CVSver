package com.example.mechulicvs.data.remote.api.community

import android.app.Application
import com.example.mechulicvs.data.Constants.Companion.BASE_URL
import com.example.mechulicvs.data.DetailPostDataSource
import com.example.mechulicvs.repository.community.DetailPostRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserDataNetworkModule {

//    @Provides
//    fun provideBaseUrl() = BASE_URL

    @Provides
    @Singleton
    fun provideRetrofit () : Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideDetailData(retrofit: Retrofit) : GetPostDetailData =
        retrofit.create(GetPostDetailData::class.java)


}