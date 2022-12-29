package com.example.mechulicvs.model

import retrofit2.http.GET

interface SendIdData {

    @GET("/users")
    suspend fun isDuplicated(
    ) : isDuplicated

}