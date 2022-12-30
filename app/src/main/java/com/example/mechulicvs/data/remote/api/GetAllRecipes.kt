package com.example.mechulicvs.data.remote.api

import com.example.mechulicvs.data.remote.model.RecipeInfoData
import retrofit2.http.GET

interface GetAllRecipes {

    @GET("/posts")
    suspend fun getAllRecipes(
    ) : RecipeInfoData

}