package com.example.mechulicvs.model

import com.google.gson.annotations.SerializedName

data class RecipeInfoData (

    @SerializedName("isSuccess") val isSuccess : Boolean,
    val message : String,
    val result : List<Recipeinfo>

)

data class Recipeinfo (

    @SerializedName("recipe_id") val recipeId : Int,
    @SerializedName("user_id") val userId : String,
    @SerializedName("user_nickname") val userNickName : String,
    @SerializedName("recipe_title") val recipeTitle : String,
    @SerializedName("recipe_reply_count") val recipeReplyCount : Int,
    @SerializedName("recipe_average_score") val recipeAverageScore : Double,
    @SerializedName("update_time") val updateTime : String,
    @SerializedName("recipe_img_url_1") val recipeImgTitle : String,

        )