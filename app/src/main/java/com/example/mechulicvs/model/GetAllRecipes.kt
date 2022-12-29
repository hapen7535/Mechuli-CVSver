package com.example.mechulicvs.model

import retrofit2.http.GET

interface GetAllRecipes {

    @GET("/posts")
    suspend fun getAllRecipes(
    ) : RecipeInfoData

}