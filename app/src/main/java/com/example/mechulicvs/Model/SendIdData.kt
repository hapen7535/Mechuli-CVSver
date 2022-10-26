package com.example.mechulicvs.Model

import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET

interface SendIdData {

    @FormUrlEncoded
    @GET("/users")
    suspend fun isDuplicated(
    ) : isDuplicated

}