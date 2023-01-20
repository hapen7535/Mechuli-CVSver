package com.example.mechulicvs.data.remote.api.community

import com.example.mechulicvs.data.remote.api.UserDataAPI
import com.example.mechulicvs.data.remote.model.detailpost.Recipe
import com.example.mechulicvs.data.remote.model.detailpost.RecipeRequest
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.POST

interface RecipeCreateService {

    @POST("/posts/recipe")
    suspend fun sendCreatedRecipe(
        @Body recipeRequest: RecipeRequest
    ) : Response<Recipe>

    companion object{
        fun getRecipeContentApi() : RecipeCreateService{
            return UserDataAPI.sendRecipeDataService
        }
    }

}