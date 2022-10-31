package com.example.mechulicvs.Model

import retrofit2.http.GET
import retrofit2.http.Path

interface GetRecomData {

    @GET("/recommends/{userId}")
    suspend fun getRecommend(
        @Path("userId") userid : String,
    ) : MenuDataResult

}