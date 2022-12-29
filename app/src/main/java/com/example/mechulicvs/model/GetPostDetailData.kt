package com.example.mechulicvs.model

import retrofit2.http.GET
import retrofit2.http.Path

interface GetPostDetailData {

    @GET("/posts/recipe/{recipeId}")
    suspend fun getDetailPostData(
        @Path("recipeId") recipeId : Int,
    ) : PostDetailData

}