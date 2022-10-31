package com.example.mechulicvs.Model

import retrofit2.http.GET
import retrofit2.http.Header

interface GetRatingList {

    @GET("/foods/result")
    suspend fun getRatingList(
    ) : MenuDataResult

}