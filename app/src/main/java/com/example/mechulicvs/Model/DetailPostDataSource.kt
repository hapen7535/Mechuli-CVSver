package com.example.mechulicvs.Model

import javax.inject.Inject

class DetailPostDataSource @Inject constructor(
    private val detailPostService : GetPostDetailData,
    private val recipeId : Int,
) {
    suspend fun getDetailPostData() = detailPostService.getDetailPostData(recipeId)
}