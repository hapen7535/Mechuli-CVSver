package com.example.mechulicvs.Model

import com.google.gson.annotations.SerializedName

data class CommentRequest (
    @SerializedName("user_id") val userId : String,
    @SerializedName("recipe_id") val recipeId : Int,
    @SerializedName("reply_content") val replyContent : String,
    val score : Double,
        )