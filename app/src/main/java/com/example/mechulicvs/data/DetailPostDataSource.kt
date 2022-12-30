package com.example.mechulicvs.data

import com.example.mechulicvs.data.remote.api.GetPostDetailData
import javax.inject.Inject

class DetailPostDataSource @Inject constructor(
    private val detailPostService : GetPostDetailData,
    private val recipeId : Int,
) {
    suspend fun getDetailPostData() = detailPostService.getDetailPostData(recipeId)
}