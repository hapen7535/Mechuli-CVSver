package com.example.mechulicvs.Model

import com.example.mechulicvs.Model.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object userDataNetworkModule {

    @Singleton
    @Provides
    fun provideBaseUrl() = BASE_URL

    @Singleton
    @Provides
    fun retrofit (baseUrl : String) : GetPostDetailData =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GetPostDetailData::class.java)


}