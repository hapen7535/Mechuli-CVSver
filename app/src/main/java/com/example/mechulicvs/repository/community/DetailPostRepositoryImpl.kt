package com.example.mechulicvs.repository.community

import com.example.mechulicvs.data.remote.api.community.DetailPostApiHelper
import com.example.mechulicvs.data.remote.api.community.GetPostDetailData
import com.example.mechulicvs.data.remote.model.PostDetailData
import com.example.mechulicvs.di.MainApplication
import retrofit2.Response
import javax.inject.Inject

class DetailPostRepositoryImpl @Inject constructor(
    private val service : GetPostDetailData
) : DetailPostApiHelper {
    override suspend fun getDetailPost(): Response<PostDetailData> {
        val recipeId = MainApplication.prefs.getInt("recipeId", 0)
        return service.getDetailPostData(recipeId)
    }
}