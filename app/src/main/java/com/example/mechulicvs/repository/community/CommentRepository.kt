package com.example.mechulicvs.repository.community

import com.example.mechulicvs.data.remote.model.CommentData
import com.example.mechulicvs.data.remote.model.CommentRequest
import com.example.mechulicvs.data.remote.api.SendCommentData
import retrofit2.Response

class CommentRepository {
    suspend fun userComment(commentRequest: CommentRequest): Response<CommentData> {
        return SendCommentData.getCommentApi().sendComment(commentRequest = commentRequest)
    }
}