package com.example.mechulicvs.data.remote.model.detailpost

import com.google.gson.annotations.SerializedName
import retrofit2.http.Field

data class RecipeRequest(
    @SerializedName("user_id") val id : String,
    @SerializedName("recipe_title") val recipeTitle : String,
    @SerializedName("recipe_ingredient") val recipeIngr : String,
    @SerializedName("recipe_cost") val recipeCost : Int,
    @SerializedName("recipe_content") val recipeContent : String,
    @SerializedName("recipe_img_url_1") val recipeImgUrl1 : String,
    @SerializedName("recipe_img_url_2") val recipeImgUrl2 : String,
    @SerializedName("recipe_img_url_3") val recipeImgUrl3 : String,
    @SerializedName("recipe_img_url_4") val recipeImgUrl4 : String,
    @SerializedName("recipe_img_url_5") val recipeImgUrl5 : String,
)
