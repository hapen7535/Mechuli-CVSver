package com.example.mechulicvs.data.remote.api.community

import com.example.mechulicvs.data.remote.model.PostDetailData
import com.example.mechulicvs.data.remote.model.detailpost.Recipe
import dagger.Provides
import retrofit2.Response


interface DetailPostApiHelper {
    suspend fun getDetailPost() : Response<PostDetailData>
}