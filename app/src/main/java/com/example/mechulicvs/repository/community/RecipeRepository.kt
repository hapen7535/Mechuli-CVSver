package com.example.mechulicvs.repository.community

import com.example.mechulicvs.data.remote.api.SendCommentData
import com.example.mechulicvs.data.remote.api.community.RecipeCreateService
import com.example.mechulicvs.data.remote.model.CommentData
import com.example.mechulicvs.data.remote.model.CommentRequest
import com.example.mechulicvs.data.remote.model.detailpost.Recipe
import com.example.mechulicvs.data.remote.model.detailpost.RecipeRequest
import retrofit2.Response

class RecipeRepository {
    suspend fun recipeContent(recipeRequest: RecipeRequest): Response<Recipe> {
        return RecipeCreateService.getRecipeContentApi().sendCreatedRecipe(recipeRequest = recipeRequest)
    }
}