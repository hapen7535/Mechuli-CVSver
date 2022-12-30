package com.example.mechulicvs.data.remote.api

import com.example.mechulicvs.data.remote.model.CommentData
import com.example.mechulicvs.data.remote.model.CommentRequest
import retrofit2.Response
import retrofit2.http.Body
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