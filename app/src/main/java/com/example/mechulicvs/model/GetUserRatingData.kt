package com.example.mechulicvs.model

import retrofit2.http.GET
import retrofit2.http.Path

interface GetUserRatingData {
    @GET("/foods/{userId}/{menuId}")
    suspend fun getUserRatingData(
        @Path("userId") userId: String,
        @Path("menuId") menuId: Int,
    ): UserRatingData
}