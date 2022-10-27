package com.example.mechulicvs.Model

import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET

interface GetMenuData{
    @FormUrlEncoded
    @GET("foods/user")
    suspend fun getSignupMenuData(
    ) : MenuDataResult
}