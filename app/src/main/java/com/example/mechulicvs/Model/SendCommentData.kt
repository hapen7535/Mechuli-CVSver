package com.example.mechulicvs.Model

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SendCommentData {

//    @FormUrlEncoded
    @POST("posts/recipe/replys")
    suspend fun sendComment(
        @Body commentRequest: CommentRequest
    ) : Response<CommentData>

    companion object{
        fun getCommentApi(): SendCommentData {
            return UserDataAPI.sendCommentDataService
        }
    }

}