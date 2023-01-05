package com.example.mechulicvs

import com.example.mechulicvs.data.remote.api.community.GetPostDetailData
import com.example.mechulicvs.data.remote.model.PostDetail
import com.example.mechulicvs.data.remote.model.PostDetailData
import com.example.mechulicvs.repository.community.DetailPostRepository
import retrofit2.Response
import javax.inject.Inject

class DetailPostRepositoryImpl @Inject constructor(
    private val service : GetPostDetailData
) : DetailPostRepository {
    override suspend fun getDetailPost(recipeId : Int): Response<PostDetailData> {
        return service.getDetailPostData(recipeId)
    }
}