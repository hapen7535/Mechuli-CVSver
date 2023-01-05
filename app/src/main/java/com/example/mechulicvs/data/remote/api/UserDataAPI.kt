package com.example.mechulicvs.data.remote.api

import com.example.mechulicvs.data.Constants.Companion.BASE_URL
import com.example.mechulicvs.data.remote.api.community.GetPostDetailData
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//
//@Module
//@InstallIn(SingletonComponent::class)
object UserDataAPI {

//    @Singleton
//    @Provides
//    fun provideRetrofit() : Retrofit = Retrofit.Builder()
//        .addConverterFactory(GsonConverterFactory.create())
//        .baseUrl(BASE_URL)
//        .build()

    private val retrofit  by lazy { //by lazy로 인해 api 변수가 사용될 때 초기화될 수 있다.
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val service : SendLoginData by lazy {
        retrofit.create(SendLoginData::class.java)
    }

    val signUpService : SendSingupData by lazy {
        retrofit.create(SendSingupData::class.java)
    }

    val signupMenuDataService : GetMenuData by lazy {
        retrofit.create(GetMenuData::class.java)
    }

    val setRatingService : SendRatingData by lazy {
        retrofit.create(SendRatingData::class.java)
    }

    val getAllRecipeService : GetAllRecipes by lazy {
        retrofit.create(GetAllRecipes::class.java)
    }

//    @Provides
//    @Singleton
//    fun provideDetailPostApiService(retrofit: Retrofit): GetPostDetailData =
//        retrofit.create(GetPostDetailData::class.java)
    val getDetailPostService : GetPostDetailData by lazy{
        retrofit.create(GetPostDetailData::class.java)
    }

    val sendCommentDataService : SendCommentData by lazy{
        retrofit.create(SendCommentData::class.java)
    }

}