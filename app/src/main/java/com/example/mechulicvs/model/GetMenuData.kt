package com.example.mechulicvs.model

import retrofit2.http.GET

interface GetMenuData{
    @GET("foods/user")
    suspend fun getSignupMenuData(
    ) : MenuDataResult
}