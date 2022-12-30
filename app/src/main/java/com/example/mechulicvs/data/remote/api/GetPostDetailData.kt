package com.example.mechulicvs.data.remote.api

import com.example.mechulicvs.data.remote.model.PostDetailData
import retrofit2.http.GET
import retrofit2.http.Path

interface GetPostDetailData {

    @GET("/posts/recipe/{recipeId}")
    suspend fun getDetailPostData(
        @Path("recipeId") recipeId : Int,
    ) : PostDetailData

}