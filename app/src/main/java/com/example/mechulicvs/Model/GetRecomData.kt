package com.example.mechulicvs.Model

import retrofit2.http.GET

interface GetRecomData {

    @GET("/recommends")
    suspend fun getRecommend(
    ) : MenuDataResult

}