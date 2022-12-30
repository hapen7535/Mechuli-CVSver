package com.example.mechulicvs.data.remote.api

import com.example.mechulicvs.data.remote.model.MenuDataResult
import retrofit2.http.GET

interface GetMenuData{
    @GET("foods/user")
    suspend fun getSignupMenuData(
    ) : MenuDataResult
}