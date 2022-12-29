package com.example.mechulicvs.Repository

import com.example.mechulicvs.model.CommentData
import com.example.mechulicvs.model.CommentRequest
import com.example.mechulicvs.model.SendCommentData
import retrofit2.Response

class CommentRepository {
    suspend fun userComment(commentRequest: CommentRequest): Response<CommentData> {
        return SendCommentData.getCommentApi().sendComment(commentRequest = commentRequest)
    }
}