package com.example.mechulicvs.Model

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SendCommentData {

    @FormUrlEncoded
    @POST("posts/recipe/replys")
    suspend fun userLogin(
        @Field("user_id") userId : String,
        @Field("recipe_id") recipeId : Int,
        @Field("reply_content") replyContent : String,
        @Field("score") score : Double,
    ) : CommentData

}