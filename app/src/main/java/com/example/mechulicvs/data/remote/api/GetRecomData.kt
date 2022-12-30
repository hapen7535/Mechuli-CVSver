package com.example.mechulicvs.data.remote.api

import com.example.mechulicvs.data.remote.model.MenuDataResult
import retrofit2.http.GET
import retrofit2.http.Path

interface GetRecomData {

    @GET("/recommends/{userId}")
    suspend fun getRecommend(
        @Path("userId") userid : String,
    ) : MenuDataResult

}