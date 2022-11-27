package com.example.mechulicvs.Repository

import com.example.mechulicvs.Model.CommentData
import com.example.mechulicvs.Model.CommentRequest
import com.example.mechulicvs.Model.SendCommentData
import retrofit2.Response

class CommentRepository {
    suspend fun userComment(commentRequest: CommentRequest): Response<CommentData> {
        return SendCommentData.getCommentApi().sendComment(commentRequest = commentRequest)
    }
}