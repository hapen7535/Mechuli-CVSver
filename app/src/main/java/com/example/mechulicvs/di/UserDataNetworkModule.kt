package com.example.mechulicvs.di

import com.example.mechulicvs.data.remote.api.community.DetailPostApiHelper
import com.example.mechulicvs.repository.community.DetailPostRepositoryImpl
import com.example.mechulicvs.data.Constants.Companion.BASE_URL
import com.example.mechulicvs.data.remote.api.community.GetPostDetailData
import com.example.mechulicvs.repository.community.DetailPostRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserDataNetworkModule {

    @Provides
    fun provideBaseUrl() = BASE_URL

    @Provides
    @Singleton
    fun provideRetrofit () : Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun provideDetailData(retrofit: Retrofit) : GetPostDetailData =
        retrofit.create(GetPostDetailData::class.java)

    @Singleton
    @Provides
    fun provideApiHelper(detailPostApiHelper: DetailPostRepositoryImpl) : DetailPostApiHelper = detailPostApiHelper

    @Singleton
    @Provides
    fun provideRemoteRepository(dataSourceImpl : DetailPostRepositoryImpl) =
        DetailPostRepository(dataSourceImpl)

}