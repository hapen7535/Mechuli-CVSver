package com.example.mechulicvs.data.remote.api

import com.example.mechulicvs.data.remote.model.isDuplicated
import retrofit2.http.GET

interface SendIdData {

    @GET("/users")
    suspend fun isDuplicated(
    ) : isDuplicated

}