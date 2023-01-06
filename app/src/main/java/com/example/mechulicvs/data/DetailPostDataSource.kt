package com.example.mechulicvs.data

import com.example.mechulicvs.di.MainApplication
import com.example.mechulicvs.data.remote.api.community.GetPostDetailData
import javax.inject.Inject

class DetailPostDataSource @Inject constructor(
    private val detailPostService : GetPostDetailData,
) {
    val recipeId = MainApplication.prefs.getInt("recipeId", 0)
    suspend fun getDetailPostDataInfo() = detailPostService.getDetailPostData(recipeId)
}