package com.example.mechulicvs.Model

import retrofit2.http.GET

interface GetRatingList {

    @GET("/foods/result")
    suspend fun getRatingList(
    ) : MenuDataResult

}