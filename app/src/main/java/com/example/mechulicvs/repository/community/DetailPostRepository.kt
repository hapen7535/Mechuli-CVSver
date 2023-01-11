package com.example.mechulicvs.repository.community

import com.example.mechulicvs.data.remote.api.community.DetailPostApiHelper
import javax.inject.Inject

class DetailPostRepository @Inject constructor(
    private val detailPostApiHelper: DetailPostApiHelper
) {
    suspend fun getDetailPost() = detailPostApiHelper.getDetailPost()
}