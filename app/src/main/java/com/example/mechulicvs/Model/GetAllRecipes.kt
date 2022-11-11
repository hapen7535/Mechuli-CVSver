package com.example.mechulicvs.Model

import retrofit2.http.GET

interface GetAllRecipes {

    @GET("/posts")
    suspend fun getAllRecipes(
    ) : RecipeInfoData

}