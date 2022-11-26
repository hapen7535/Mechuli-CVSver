package com.example.mechulicvs.Model

import com.google.gson.annotations.SerializedName

data class CommentData (
    @SerializedName("user_id") val userId : String,
    @SerializedName("recipe_id") val recipeId : Int,
    @SerializedName("reply_content") val content : String,
    val score : Double,
        )